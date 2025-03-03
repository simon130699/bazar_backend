package com.todocodeacademy.bazar.service;

import com.todocodeacademy.bazar.dto.VentaClienteProductoDTO;
import com.todocodeacademy.bazar.model.Producto;
import com.todocodeacademy.bazar.model.Venta;
import com.todocodeacademy.bazar.repository.IProductoRepository;
import com.todocodeacademy.bazar.repository.IVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private IVentaRepository repoVenta;
    @Autowired
    private IProductoRepository repoProducto;

    private ProductoService prod;

    @Override

    public void crearVenta(Venta venta) {
        if(venta.getListaProductos()!=null){
            for(Producto p:venta.getListaProductos()){
                //traemos de la BD los productos
                Producto productoDB = repoProducto.findById(p.getCodigo_producto()).orElse(null);
                //el total de venta no puede ser 0 si no se setea a 1
                if(p.getTotalVenta()==0){
                    p.setTotalVenta(1);
                }

                if (productoDB == null) {
                    throw new RuntimeException("El producto con ID " + p.getCodigo_producto() + " no existe en la base de datos");
                }
                //creee la nueva cantidad
                double nuevaCantidad = productoDB.getCantidad_disponible() - p.getTotalVenta();

                if (nuevaCantidad < 0) {
                    throw new RuntimeException("No hay suficiente stock para el producto: " + productoDB.getNombre());
                }
                //setee la nueva cantidad
                productoDB.setCantidad_disponible(nuevaCantidad);
                //guarde el objeto nuevo en la BD
                repoProducto.save(productoDB);
            }
        }
        repoVenta.save(venta);
    }
    @Override
    public List<Venta> traerVentas() {
        List<Venta> listaVentas = repoVenta.findAll();
        return listaVentas;
    }

    @Override
    public Venta traerUnaVenta(Long codigo_venta) {
       Venta venta = repoVenta.findById(codigo_venta).orElse(null);
       return venta;
    }

    @Override
    public void eliminarVenta(Long codigo_venta) {
        repoVenta.deleteById(codigo_venta);
    }

    @Override
    public void editarVenta(Venta prodEdit) {
        this.crearVenta(prodEdit);
    }

    //5. Obtener la lista de productos de una determinada venta
    //a. Métodos HTTP: GET
    //b. Endpoint:
    //localhost:8080/ventas/productos/{codigo_venta}
    @Override
    public List<Producto> traerProductosDeVenta(Long codigo_venta) {
        Venta vent= traerUnaVenta(codigo_venta);
        return vent.getListaProductos();
    }
    //6. Obtener la sumatoria del monto y también cantidad total de ventas de un determinado
    //día
    //a. Métodos HTTP: GET
    //b. Endpoint:
    //localhost:8080/ventas/{fecha_venta}
    public int sumatoriaDia(String fecha_venta){
        LocalDate fecha = LocalDate.parse(fecha_venta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int sumatoria=0;
        for(Venta vent : traerVentas()){
            if(vent.getFecha_venta().equals(fecha)){
                sumatoria+=vent.getTotal();
            }
        }
        return sumatoria;
    }

 //7. Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
 //apellido del cliente de la venta con el monto más alto de todas.
 //a. Métodos HTTP: GET
 //b. Endpoint:
 //localhost:8080/ventas/mayor_venta
 //Tener en cuenta patrón DTO para este escenario
 @Override
 public VentaClienteProductoDTO ventaMayor() {
     if (traerVentas().isEmpty()) {
         return null;
     }
     // Busco la venta con mayor total
     // Los streams en Java permiten realizar operaciones
     // de procesamiento funcional sobre las colecciones
     Venta ventaMayor = traerVentas().stream()
             .max(Comparator.comparing(Venta::getTotal))
             .orElse(null);

     if (ventaMayor == null) {
         return null;
     }

     // Convierto la venta en DTO
     VentaClienteProductoDTO dto = new VentaClienteProductoDTO();
     dto.setCodigo_venta(ventaMayor.getCodigo_venta());
     dto.setTotal(ventaMayor.getTotal());
     dto.setCantProductos(ventaMayor.getListaProductos().size());

     if (ventaMayor.getUnCliente() != null) {
         dto.setNombreCliente(ventaMayor.getUnCliente().getNombre());
         dto.setApellidoCliente(ventaMayor.getUnCliente().getApellido());
     } else {
         dto.setNombreCliente("Cliente no disponible");
         dto.setApellidoCliente("");
     }

     return dto;
 }

}
