package com.luxsoft.sx4.distribucion


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat

import org.apache.commons.lang.time.DateUtils

/**
 * 
 * 
 * @author Ruben Cancino Ramos
 * 
 */
@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='documento,tipoDeDocumento')
class Entrega {

	

	//String origen
	String documento
	String tipoDeDocumento
	Date fechaDeDocumento
	
	Boolean parcial=false
	BigDecimal totalDocumento=0.0
	
	String cliente
	String nombre
	
	int paquetes = 0
	BigDecimal kilos = 0
	BigDecimal cantidad = 0

	Date arribo
	Date recepcion
	String recibio

	String comentario

	BigDecimal valor=0.0
	BigDecimal porCobrar=0.0
	BigDecimal comision=1.1
	BigDecimal comisionPorTonelada=0.0
	BigDecimal importeComision=0.0
	Date fechaComision
	String comentarioDeComision
	

	//Surtido surtido
	//Date recepcionDeSurtido
	

	static hasMany = [partidas: EntregaDet]

	static belongsTo = [embarque: Embarque]

    static constraints = {
    	
    	documento maxSize:15
		tipoDeDocumento maxSize:10
		totalDocumento scale:4
		cliente maxSize:20
		kilos scale:4
		cantidad scale:4

		arribo(nullable:true)
		recepcion(nullable:true)
		recibio nullable:true
		comentario nullable:true
		valor(scale:4)
		porCobrar scale:4
		comision scale:4
		
		fechaComision nullable:true
		comentarioDeComision nullable:true
		comisionPorTonelada scale:4
		//surtido nullable:true
		//recepcionDeSurtido nullable:true
	}

	static mapping = {
		partidas cascade: "all-delete-orphan"
	}


	/**
	 * Actualiza los importes de la unidad
	 */
	public void actualziarValor() {
		def total=0.0
		if(factura){
			if(parcial){
				valor=venta.importeBruto-venta.importeDescuento
			}else{
				/*
				BigDecimal total=BigDecimal.ZERO;				
				for(EntregaDet det:getPartidas()){
					det.actualizar();
					total=total.add(det.getValor());
				}
				setValor(total);
				*/
				
				valor=partidas.sum 0.0,{it.valor}
			}
		}
		valor=total
	}
	
	/*
	public void actualizarKilosCantidad(){
		if(getFactura()!=null){
			if(!isParcial()){				
				double kilos = 0;
				double cantidad=0;
				for (VentaDet det : getFactura().getPartidas()) {
					kilos = kilos + det.getKilos();
					cantidad=cantidad+det.getCantidadEnUnidad();
				}
				setKilos(kilos);
				setCantidad(Math.abs(cantidad));
			}else{
				double kilos = 0;
				double cantidad=0;
				for (EntregaDet det : partidas) {
					double factor=det.getVentaDet().getFactor();
					double cantiUni=det.getCantidad()/factor;
					double kg=det.getProducto().getKilos();
					kilos = kilos+(cantiUni*kg);
					cantidad+=cantiUni;
				}
				setKilos(kilos);
				setCantidad(Math.abs(cantidad));
			}
		}
	}
	*/
	
	/*
	public void actualizarComision(){
		
		CantidadMonetaria imp=CantidadMonetaria.pesos(getValor());
		
		
		// Caso Clientes especiales
		if(getComisionPorTonelada()>0){
			double toneladas=getKilos()/1000;
			imp=CantidadMonetaria.pesos(getComisionPorTonelada());
			setImporteComision(imp.multiply(toneladas).amount());
			return;
		}
		BigDecimal importeBase=getFactura().getImporteBruto().subtract(getFactura().getImporteDescuento()); 
		//Caso  Sucursal Bolivar		
		if(getFactura().getSucursal().getId()==5L && !getFactura().getOrigen().equals(OrigenDeOperacion.CRE)){
			if(importeBase.doubleValue()<20000.00){
				if(!isParcial()){
					setComision(1.3);
				}				
			}
		}
		double com=getComision()/100;
		CantidadMonetaria impCom=imp.multiply(com);
		setImporteComision(impCom.amount());		
		
			
	}

	public int getRetraso(){
		if(getEmbarque().getRegreso()==null){
			return 0;
		}
		Date pedido=getFactura().getPedidoCreado();
		Date regreso=getEmbarque().getRegreso();
		if(regreso==null){
			regreso=new Date(System.currentTimeMillis());
		}
		long mils=regreso.getTime()-pedido.getTime();
		int horas=(int)(mils/(60*60*1000));
		return horas;

	}
	
	
	public String getRetrasoCalculado(){
		Date pedido=getFactura().getPedidoCreado();
		if(pedido==null)
			pedido=getFactura().getLog().getCreado();
		Date regreso=getEmbarque().getRegreso();
		if(regreso==null){
			regreso=new Date(System.currentTimeMillis());
		}
		long mils=regreso.getTime()-pedido.getTime();
		double minutos=(int)(mils/(60*1000));
		int hrs=(int) Math.floor(minutos/60);
		double min=minutos-(hrs*60);
				
		String res=hrs+":"+min;
		return res;

	}
	*/
	
}

