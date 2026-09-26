package model;
public class Movimiento {
    private Integer idMovimiento;
    private Integer idProveedor;
    private Integer idProducto;
    private String tipoMovimiento;
    private int cantidad;
    private String fechaMovimiento; //se formaterará en Spring Boot

    public Movimiento(Integer idMovimiento, Integer proveedor, Integer producto, String tipoMovimiento, int cantidad, String fechaMovimiento){
        this.idMovimiento = idMovimiento;
        this.idProveedor = proveedor;
        this.idProducto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
    }
    


    public Integer getIdMovimiento(){return this.idMovimiento;}
    public Integer getIdProveedor(){return this.idProveedor;}
    public Integer getIdProducto(){return this.idProducto;}
    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public int getCantidad(){return this.cantidad;}
    public String getFechaMovimiento(){return this.fechaMovimiento;}

    
    public void setTipoMovimiento(String tipoMovimiento){
        this.tipoMovimiento = tipoMovimiento;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
