/**
 * Created by inaranjo on 2017-04-28.
 */
$(function(){

    $("#btnContinuar").hide();

    $("#btnGuardarAdicional").click(function(e){

        if($("#cupoOtorgado").val() == ''){
            alert("El campo cupo otorgado se encuetra vacio.");
            e.preventDefault();
        }else if ($("#cedula").val() == ''){
            alert("El campo cedula se encuetra vacio.");
            e.preventDefault();
        }else if ($("#cedula").val() == $("#identificacion").val()){
            alert("La cedula del adicional no puede ser la misma del principal.");
            e.preventDefault();
        }else{
            guardadarAdicional();
        }
    });

    $("#substatus").change(function () {
        if($(this).find(":selected").text() == 'CU4 VOLVER A LLAMAR' || $(this).find(":selected").text() == 'CU5 NO DESEA EL PRODUCTO' ||
            $(this).find(":selected").text() == 'CU6 NO CUMPLE EL PERFIL' || $(this).find(":selected").text() == 'CU7 YA TOMO EL PRODUCTO' ||
            $(this).find(":selected").text() == 'NU1 REGESTIONABLES' || $(this).find(":selected").text() == 'NU2 INUBICABLES'){
            $("#btnContinuar").show();
        }else{
            $("#btnContinuar").hide();
        }
    })
});

function guardadarAdicional() {

    $formulario = $("#formAdicional").serializeArray()

    $.ajax({
        url: baseUrl + "/Gestion/saveAditional",
        type: 'POST',
        dataType: "json",
        data: $formulario,
        success: function (data) {
            console.log("DONE");
            $transId = data;

            if (data == true) {
                $("#btnContinuar").show();
                limpiarFormularioAdicional();
                $("#modal").modal("hide");
                alert("Adicional ingresado correctamente");
            }

            if (data == false) {
                alert("Lo sentimos, no se pudo guardar tus datos");
            }
        }
    });
}
function limpiarFormularioAdicional(){

    $("#segundoApellido").val("");
    $("#segundoNombre").val("");
    $("#cedula").val("");
    $("#fechaNacimiento").val("");
    $("#nacionalidad").val("");
    $("#observaciones").val("");
    $("#primerApellido").val("");
    $("#primerNombre").val("");
}
