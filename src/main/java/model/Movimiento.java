package model;
public class Movimiento {
    private Integer idMovimiento;
    private Integer proveedor;
    private Integer producto;
    private String tipoMovimiento;
    private int cantidad;
    private String fechaMovimiento; //se formaterará en Spring Boot

    public Movimiento(Integer idMovimiento, Integer proveedor, Integer producto, String tipoMovimiento, int cantidad, String fechaMovimiento){
        this.idMovimiento = idMovimiento;
        this.proveedor = proveedor;
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
    }
    


    public Integer getIdMovimiento(){return this.idMovimiento;}
    public Integer getProveedor(){return this.proveedor;}
    public Integer getProducto(){return this.producto;}
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
