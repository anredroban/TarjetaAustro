$(document).ready(function(){
//	DateRangePicker
	function cb(start, end) {
        $('#reportrange span').html(start.format('YYYY/MM/DD') + ' - ' + end.format('YYYY/MM/DD'));
    }
    cb(moment().subtract(29, 'days'), moment());

    $('#reportrange').daterangepicker({
        ranges: {
           'Hoy': [moment(), moment()],
           'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Últimos 7 días': [moment().subtract(6, 'days'), moment()],
           'Últimos 30 días': [moment().subtract(29, 'days'), moment()],
		   'Esta semana': [moment().startOf('week').add(1, 'days'), moment().endOf('week').add(1, 'days')],
           'Este mes': [moment().startOf('month'), moment().endOf('month')],		   
           'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
		locale: {     
	    "format": 'YYYY/MM/DD',
        "separator": " - ",
        "applyLabel": "Aplicar",
        "cancelLabel": "Cancelar",        
        "customRangeLabel": "Elegir Rango",
        "daysOfWeek": [
            "Do",
            "Lu",
            "Ma",
            "Mi",
            "Ju",
            "Vi",
            "Sa"
        ],
        "monthNames": [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        ],
        "firstDay": 1
    }
		
    }, cb);
    
    
    $("#btnreporteventasporciudad").click(function(){
    	var fechas = $("#reportrange").val().split('-');
    	var fechaInicio = fechas[0].replace('/', '-').trim().replace('/', '-') + " 00:00:00";
    	var fechaFin = fechas[1].replace('/', '-').trim().replace('/', '-') + " 23:59:59";
    	var nombreBase = $("#nombrebase").val();
    	var usuario = $("#usuario").val();    	
    	
    	$.get("/CallCenter/funcionesAjax/getGraficoVentasCiudadReporte"
    			, {fechaInicio: fechaInicio, fechaFin: fechaFin, nombreBase: nombreBase, usuario: usuario}
    			, function(data){    				
    				if(Object.keys(data).length > 0){    					
    					var ventasQuito = new Array();
    					var ventasGuayaquil = new Array();
    					ventasQuito[0] = 'Quito';
    					if(data['QUITO'])
    						ventasQuito[1] = data['QUITO'];
    					else
    						ventasQuito[1] = 0;
    					ventasGuayaquil[0] = 'Guayaquil';
    					
    					if(data['GUAYAQUIL'])
    						ventasGuayaquil[1] = data['GUAYAQUIL'];
    					else
    						ventasGuayaquil[1] = 0;
    					
    					$('#container').highcharts({
    				        chart: {
    				            plotBackgroundColor: null,
    				            plotBorderWidth: 0,
    				            plotShadow: false
    				        },
    				        title: {
    				            text: 'Ventas<br>por<br>Ciudad',
    				            align: 'center',
    				            verticalAlign: 'middle',
    				            y: 40
    				        },
    				        tooltip: {
    				            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    				        },
    				        plotOptions: {
    				            pie: {
    				                dataLabels: {
    				                    enabled: true,
    				                    distance: -50,
    				                    style: {
    				                        fontWeight: 'bold',
    				                        color: 'white',
    				                        textShadow: '0px 1px 2px black'
    				                    }
    				                },
    				                startAngle: -90,
    				                endAngle: 90,
    				                center: ['50%', '75%']
    				            }
    				        },
    				        series: [{
    				            type: 'pie',
    				            name: 'Ventas por Ciudad',
    				            innerSize: '50%',
    				            data: [
    				                ventasQuito,
    				                ventasGuayaquil
    				            ]
    				        }]
    				    });
    					$("#containertabla").html("<table class='table'><thead><tr><th>Ciudad</th><th>Cantidad</th></tr></thead><tbody><tr><td>Quito</td><td>"+ventasQuito[1]+"</td></tr><tr><td>Guayaquil</td><td>"+ventasGuayaquil[1]+"</td></tr></tbody></table>");
    					}else{
    						$('#container').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
    					}
    			});
    });
    
});