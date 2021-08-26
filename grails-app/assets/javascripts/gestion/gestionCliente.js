$(document).ready(function() {

  $("#productoAceptado").val($("#productoAceptado option:first").val());
  $("#tipoDireccion").val($("#tipoDireccion option:first").val());
  // $("#provinciaDomicilio").val($("#provinciaDomicilio option:first").val());
  $("#provinciaTrabajo").val($("#provinciaTrabajo option:first").val());

//PARA LAS PROVINCIAS
  $("#provinciaDomic").change(function(data) {

     if ($("#provinciaDomic").val() == '') {
         var selectCiudades = document.getElementById("ciudadDomic");
         var opcion = document.createElement('option');
         var numeroElementos = selectCiudades.length;

         while (numeroElementos > 0) {
           numeroElementos--;
           selectCiudades.remove(numeroElementos);
         }

         opcion.text = '-- Seleccione --';
         opcion.value = '';
         selectCiudades.add(opcion, null);
     } else {
         $idCiudad = this.value;
         $.get(baseUrl + "/funcionesAjax/getCiudades", {
             id : $idCiudad
         }, function(data) {
           //Cuantos subestados vinieron
           var tamCiudades = data[0].length;
           //Recupero el select de los subestados
           var selectCiudades = document.getElementById("ciudadDomic");
           var numeroElementos = selectCiudades.length;

           while (numeroElementos > 0) {
             numeroElementos--;
             selectCiudades.remove(numeroElementos);
           }

           var opt = document.createElement('option');
           opt.text = '-- Seleccione --';
           opt.value = '';
           selectCiudades.add(opt, null);

           for (i = 0; i < tamCiudades; i++) {
               var opcion = document.createElement('option');
               opcion.text = data[1][i];
               opcion.value = data[0][i];
               selectCiudades.add(opcion, null);
           }
         });
     }
  });

  $("#provinciaTrab").change(function(data) {

   if ($("#provinciaTrab").val() == '') {
       var selectCiudades = document.getElementById("ciudadTrab");
       var opcion = document.createElement('option');

       var numeroElementos = selectCiudades.length;
       while (numeroElementos > 0) {
           numeroElementos--;
           selectCiudades.remove(numeroElementos);
       }

       opcion.text = '-- Seleccione --';
       opcion.value = ''
       selectCiudades.add(opcion, null);
   } else {
       $idCiudad = this.value;
       $.get(baseUrl + "/funcionesAjax/getCiudades", {
               id : $idCiudad
           },function(data) {
           //Cuantos subestados vinieron
           var tamCiudades = data[0].length;
           //Recupero el select de los subestados
           var selectCiudades = document.getElementById("ciudadTrab");

           var numeroElementos = selectCiudades.length;

           while (numeroElementos > 0) {
               numeroElementos--;
               selectCiudades.remove(numeroElementos);
           }

           var opt = document.createElement('option');
           opt.text = '-- Seleccione --';
           opt.value = '';
           selectCiudades.add(opt, null);

           for (i = 0; i < tamCiudades; i++) {
               var opcion = document.createElement('option');
               opcion.text = data[1][i];
               opcion.value = data[0][i];
               selectCiudades.add(opcion, null);
           }
       });
   }
  });

  $("#ciudadDomic").change(function(data) {

      if ($("#ciudadDomic").val() == '') {
          var selectParroquias = document.getElementById("sectorDomic");
          var opcion = document.createElement('option');

          var numeroElementos = selectParroquias.length;

          while (numeroElementos > 0) {
              numeroElementos--;
              selectParroquias.remove(numeroElementos);
          }

          opcion.text = '-- Seleccione --';
          opcion.value = ''
          selectParroquias.add(opcion, null);
      } else {
          $idCiudad = this.value;

          $.get(
              baseUrl + "/funcionesAjax/getParroquias",
              {
                  id : $idCiudad
              }, function(data) {
                  //Cuantos subestados vinieron
                  var tamParroquias = data[0].length;
                  //Recupero el select de los subestados
                  var selectParroquias = document.getElementById("sectorDomic");
                  var numeroElementos = selectParroquias.length;

                  while (numeroElementos > 0) {
                      numeroElementos--;
                      selectParroquias.remove(numeroElementos);
                  }

                  var opt = document.createElement('option');
                  opt.text = '-- Seleccione --';
                  opt.value = '';
                  selectParroquias.add(opt, null);

                  for (i = 0; i < tamParroquias; i++) {
                      var opcion = document.createElement('option');
                      opcion.text = data[1][i];
                      opcion.value = data[0][i];
                      selectParroquias.add(opcion, null);
                  }
              });
            }
        });


    $("#ciudadTrab").change(function(data) {

        if ($("#ciudadDomic").val() == '') {
            var selectParroquias = document.getElementById("sectorTrab");
            var opcion = document.createElement('option');

            var numeroElementos = selectParroquias.length;

            while (numeroElementos > 0) {
                numeroElementos--;
                selectParroquias.remove(numeroElementos);

            }

            opcion.text = '-- Seleccione --';
            opcion.value = ''
            selectParroquias.add(opcion, null);
        } else {
            $idCiudad = this.value;

            $.get(
                baseUrl + "/funcionesAjax/getParroquias",
                {
                    id : $idCiudad
                }, function(data) {
                    //Cuantos subestados vinieron
                    var tamParroquias = data[0].length;
                    //Recupero el select de los subestados
                    var selectParroquias = document.getElementById("sectorTrab" +
                        "");

                    var numeroElementos = selectParroquias.length;

                    while (numeroElementos > 0) {
                        numeroElementos--;
                        selectParroquias.remove(numeroElementos);
                    }

                    var opt = document.createElement('option');
                    opt.text = '-- Seleccione --';
                    opt.value = '';
                    selectParroquias.add(opt, null);

                    for (i = 0; i < tamParroquias; i++) {
                        var opcion = document.createElement('option');
                        opcion.text = data[1][i];
                        opcion.value = data[0][i];
                        selectParroquias.add(opcion, null);
                    }
                });
        }
    });

    //Validaciones Cesar
    $('#telefonoDomContacto').on('keypress', function(e) {
        if(checkIfNumberNoSpace(e.which, e)==0){
            return false;
        }else{
            return;
        }
    });

    $('#celularContacto').on('keypress', function(e) {
        if(checkIfNumberNoSpace(e.which, e)==0){
            return false;
        }else{
            return;
        }
    });


    $('#nombreContacto').on('keypress', function(e) {
        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#callePrincipalDomic').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#callePrincipalTrab').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#numeracionDomic').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#numeracionTrab').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#calleTransversalDomic').on('keypress', function(e) {
        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#calleTransversalTrab').on('keypress', function(e) {
        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });


    $('#referenciaDomic').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });

    $('#referenciaTrab').on('keypress', function(e) {

        if(validateEnter(e.which, e)==0){
            return false;
        }else{
            return true;
        }

        $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
        $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
    });
});

function checkIfNumberNoSpace(codeKey,e){
    if (codeKey == 32)
        return 0;
    // Asugarndo numero y no espacios
    if ($.inArray(codeKey, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A
        (codeKey == 97 && e.ctrlKey === true) ||
        // Allow: Ctrl+C
        (codeKey == 99 && e.ctrlKey === true) ||
        // Allow: Ctrl+X
        (codeKey == 120 && e.ctrlKey === true) ||
        // Allow: home, end, left, right, tab
        (codeKey == 0)) {
        // let it happen, don't do anything
        return 1;
    }
    if ((codeKey < 48 || codeKey > 57)) {
        return 0;
    }
}

function removeDoubleSpace(text){
    text = text.replace(/\s\s+/g, ' ');
    return text;
}

function isValidString(text){
    text = text.replace(/[!@#$%^&*()=+{}[\]/?¿_;:'"/<>`~|]/,'');
    return text;
}

function validateEnter(codeKey,e) {
    if (codeKey == 13) {
        return 0;
    }else{
        return 1
    }
}
