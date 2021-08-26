$(function () {

    $("#btnguardar").on("click", function (e) {

        if ($("#estados").val() == "") {
            alert("Debe escoger un estado");
            e.preventDefault();
            return false;
        } else {
            if ($("#subestados").val() == "") {
                alert("Debe escoger un subestado");
                e.preventDefault();
                return false;
            } else {
                if ($("#subestados").val() == "6") {
                    if ($("#motivonoacepta").val() == "") {
                        alert("Debe escoger un motivo por el que no acepta el producto");
                        e.preventDefault();
                        return false;
                    }
                }
            }
        }

        if ($("#subestados").val() == "1") {
            if ($("#productoAceptado").val() == '') {
                alert("Producto aceptado vacío");
                e.preventDefault();
                return false;
            }

            if ($("#personaContacto").val() == '') {
                alert("Persona de contacto vacío");
                e.preventDefault();
                return false;
            }

            if ($("#telefonoContacto").val() == '') {
                alert("Teléfono de casa vacío");
                e.preventDefault();
                return false;
            } else {
                if ($("#telefonoContacto").val().length != 9) {
                    alert("El teléfono de casa debe tener 9 dígitos");
                    e.preventDefault();
                    return false;
                }
            }

            if ($("#telefonoTrabajoGestion").val() == '') {
                alert("Teléfono de trabajo vacío");
                e.preventDefault();
                return false;
            } else {
                if ($("#telefonoTrabajoGestion").val().length != 9) {
                    alert("El teléfono de trabajo debe tener 9 dígitos");
                    e.preventDefault();
                    return false;
                }
            }

            if ($("#celularContacto").val() == '') {
                alert("Celular de contacto vacío");
                e.preventDefault();
                return false;
            } else {
                if ($("#celularContacto").val().length != 10) {
                    alert("El celular de contacto debe tener 10 dígitos");
                    e.preventDefault();
                    return false;
                }
            }

            if ($("#provinciaDomicilio").val() == '') {
                alert("Provincia Domicilio vacío");
                e.preventDefault();
                return false;
            }

            if ($("#ciudad").val() == '') {
                alert("Cantón Domicilio vacío");
                e.preventDefault();
                return false;
            }

            if ($("#sector").val() == '') {
                alert("Parroquia Domicilio vacío");
                e.preventDefault();
                return false;
            }

            if ($("#callePrincipal").val() == '') {
                alert("Calle Principal Domicilio vacío");
                e.preventDefault();
                return false;
            }

            if ($("#referencia").val() == '') {
                alert("Referencia Domicilio vacío");
                e.preventDefault();
                return false;
            }

            //Direccion Trabajo
            if ($("#provinciaTrabajo").val() == '') {
                alert("Provincia Trabajo vacío");
                e.preventDefault();
                return false;
            }

            if ($("#ciudadTrabajo").val() == '') {
                alert("Cantón Trabajo vacío");
                e.preventDefault();
                return false;
            }

            if ($("#sectorTrabajo").val() == '') {
                alert("Parroquia Trabajo vacío");
                e.preventDefault();
                return false;
            }


            if ($("#callePrincipalTrabajo").val() == '') {
                alert("Calle Principal Trabajo vacío");
                e.preventDefault();
                return false;
            }

            if ($("#referenciaTrabajo").val() == '') {
                alert("Referencia Trabajo vacío");
                e.preventDefault();
                return false;
            }

            if ($("#tipoDireccion").val() == '') {
                alert("Dirección Entrega vacío");
                e.preventDefault();
                return false;
            }

            if ($("#horarioEntrega1").val() == '') {
                alert("Horario Entrega vacío");
                e.preventDefault();
                return false;
            }

            if ($("#horarioEntrega2").val() == '') {
                alert("Horario Entrega vacío");
                e.preventDefault();
                return false;
            }

            if ($("#facturacion").val() == '') {
                alert("Campo Facturación vacío");
                e.preventDefault();
                return false;
            }

            //Se valida el tamaño de informacion concatenada
            $infoConcDomicilio = $("#callePrincipal").val() + ' ' + $("#nomenclatura").val() + ' ' + $("#calleSecundaria").val() + ' ' + $("#edificio").val() + ' PRRQ ' + $("#sector option:selected").html() + ' ' + $("#referencia").val();
            $infoConcDomicilio = replaceMore2Spaces($infoConcDomicilio);
            $infoConcTrabajo = $("#callePrincipalTrabajo").val() + ' ' + $("#nomenclaturaTrabajo").val() + ' ' + $("#calleSecundariaTrabajo").val() + ' ' + $("#edificioTrabajo").val() + ' PRRQ ' + $("#sectorTrabajo option:selected").html() + ' ' + $("#referenciaTrabajo").val();
            $infoConcTrabajo = replaceMore2Spaces($infoConcTrabajo);

            if ($infoConcDomicilio.length > 150 || $infoConcDomicilio.length < 100) {
                alert("El total de la dirección de domicilio tiene " + $infoConcDomicilio.length + " caracteres. El número de caracteres permitido es de 100 a 150.");
                e.preventDefault();
                return false;
            }

            if ($infoConcTrabajo.length > 150 || $infoConcTrabajo.length < 100) {
                alert("El total de la dirección de trabajo tiene " + $infoConcTrabajo.length + " caracteres. El número de caracteres permitido es de 100 a 150.");
                e.preventDefault();
                return false;
            }

        }

    })
})

function replaceMore2Spaces(text) {
    var resultText = text;
    while (true) {
        if (resultText.indexOf("  ") != -1) {
            resultText = resultText.replace(/\s{2,}/, " ");
        }else{
            break;
        }
    }
    return resultText.trim();
}