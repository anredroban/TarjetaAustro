$(document).ready(function () {
    $("#btnAgregarReferido").click(function () {
        $("#modalReferido").modal('show');
    });

    $("#btnGuardarReferido").click(function(e){
        $cedula = $("#referidoCedula").val().trim();
        $nombres = $("#referidoNombres").val();
        $apellidos = $("#referidoApellidos").val();

        if($cedula == ""){
            alert("Debe ingresar el número de cédula del cliente");
            e.preventDefault();
            return false;
        }else{
            if($cedula.length != 10){
                alert("Ingrese 10 dígitos para la cédula");
                e.preventDefault();
                return false;
            }
        }

        if($nombres == "" || $apellidos == ""){
            alert("Ingrese nombres y apellidos del referido");
            e.preventDefault();
            return false;
        }

        $.post(baseUrl + "/FuncionesAjax/GuardarReferido/", {cedula: $cedula, nombres: $nombres, apellidos: $apellidos}, function(data){
            alert(data);
            location.reload();
        });
    });

});