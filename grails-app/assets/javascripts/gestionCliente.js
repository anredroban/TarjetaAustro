$(document)
			.ready(
					function() {
						$("#estados").val($("#estados option:first").val());
						$("#motivonoacepta").val($("#motivonoacepta option:first").val());

                        $("#productoAceptado").val($("#productoAceptado option:first").val());
                        $("#tipoDireccion").val($("#tipoDireccion option:first").val());
                        // $("#provinciaDomicilio").val($("#provinciaDomicilio option:first").val());
                        $("#provinciaTrabajo").val($("#provinciaTrabajo option:first").val());
                        $("#horarioEntrega1").val($("#horarioEntrega option:first").val());
                        $("#horarioEntrega2").val($("#horarioEntrega option:first").val());
                        $("#facturacion").val($("#facturacion option:first").val());


						$("#datosgestion").hide();
						$("#noacepta").hide();
						$("#rellamadadtp").hide();

						$("#estados")
								.change(function(data) {

											$("#noacepta").hide();
											$("#rellamadadtp").hide();

											if ($("#estados").val() != "1") {
												$("#datosgestion").hide();
											}else{

											}
											if ($("#estados").val() == '') {
												var selectSubestados = document
														.getElementById("subestados");
												var opcion = document
														.createElement('option');

												var numeroElementos = selectSubestados.length;
												while (numeroElementos > 0) {
													numeroElementos--;
													selectSubestados
															.remove(numeroElementos);
												}

												opcion.text = '-- Seleccione --';
												opcion.value = ''
												selectSubestados.add(opcion,
														null);
											} else {
												$idEstado = this.value;
												$
														.get(
																baseUrl + "/funcionesAjax/getSubestados",
																{
																	id : $idEstado
																},
																function(data) {
																	//Cuantos subestados vinieron
																	var tamSubestados = data[0].length;
																	//Recupero el select de los subestados
																	var selectSubestados = document
																			.getElementById("subestados");

																	var numeroElementos = selectSubestados.length;
																	while (numeroElementos > 0) {
																		numeroElementos--;
																		selectSubestados
																				.remove(numeroElementos);
																	}
																	var opt = document
																			.createElement('option');
																	opt.text = '-- Seleccione --';
																	opt.value = '';
																	selectSubestados
																			.add(
																					opt,
																					null);
																	for (i = 0; i < tamSubestados; i++) {
																		var opcion = document
																				.createElement('option');
																		opcion.text = data[1][i];
																		opcion.value = data[0][i];
																		selectSubestados
																				.add(
																						opcion,
																						null);
																	}
																});
											}

										});

						$("#subestados").change(function(data) {
							if ($("#subestados").val() == "1") {
								$("#datosgestion").show();
								$("#telefonogestion").on('keypress', function(e) {
							        if(checkIfNumberNoSpace(e.which, e)==0){
							        	return false;
							        }else{
							        	return;
							        }
							    });
								$("#celulargestion").on('keypress', function(e) {
									if(checkIfNumberNoSpace(e.which, e)==0){
						        		return false;
						        	}else{
						        		return;
						        	}
								});
							} else {
								$("#datosgestion").hide();
							}
							$("#motivonoacepta").val($("#motivonoacepta option:first").val());
							if ($("#subestados").val() == "6") {
								$("#noacepta").show();
							} else {
								$("#noacepta").hide();
							}
							if ($("#subestados").val() == "4") {
								$("#rellamadadtp").show();
							} else {
								$("#rellamadadtp").hide();
							}
						});

						//PARA LAS PROVINCIAS
                        $("#provinciaDomicilio")
                            .change(function(data) {



                                var selectParroquias = document
                                    .getElementById("sector");
                                var opcion = document
                                    .createElement('option');

                                var numeroElementos = selectParroquias.length;
                                while (numeroElementos > 0) {
                                    numeroElementos--;
                                    selectParroquias
                                        .remove(numeroElementos);
                                }

                                opcion.text = '-- Seleccione --';
                                opcion.value = ''
                                selectParroquias.add(opcion,
                                    null);



                                if ($("#provinciaDomicilio").val() == '') {
                                    var selectCiudades = document
                                        .getElementById("ciudad");
                                    var opcion = document
                                        .createElement('option');

                                    var numeroElementos = selectCiudades.length;
                                    while (numeroElementos > 0) {
                                        numeroElementos--;
                                        selectCiudades
                                            .remove(numeroElementos);
                                    }

                                    opcion.text = '-- Seleccione --';
                                    opcion.value = ''
                                    selectCiudades.add(opcion,
                                        null);
                                } else {
                                    $idCiudad = this.value;
                                    $
                                        .get(
                                            baseUrl + "/funcionesAjax/getCiudades",
                                            {
                                                id : $idCiudad
                                            },
                                            function(data) {
                                                //Cuantos subestados vinieron
                                                var tamCiudades = data[0].length;
                                                //Recupero el select de los subestados
                                                var selectCiudades = document
                                                    .getElementById("ciudad");

                                                var numeroElementos = selectCiudades.length;
                                                while (numeroElementos > 0) {
                                                    numeroElementos--;
                                                    selectCiudades
                                                        .remove(numeroElementos);
                                                }
                                                var opt = document
                                                    .createElement('option');
                                                opt.text = '-- Seleccione --';
                                                opt.value = '';
                                                selectCiudades
                                                    .add(
                                                        opt,
                                                        null);
                                                for (i = 0; i < tamCiudades; i++) {
                                                    var opcion = document
                                                        .createElement('option');
                                                    opcion.text = data[1][i];
                                                    opcion.value = data[0][i];
                                                    selectCiudades
                                                        .add(
                                                            opcion,
                                                            null);
                                                }
                                            });
                                }

                            });

                        $("#provinciaTrabajo")
                            .change(function(data) {



                                var selectParroquias = document
                                    .getElementById("sectorTrabajo");
                                var opcion = document
                                    .createElement('option');

                                var numeroElementos = selectParroquias.length;
                                while (numeroElementos > 0) {
                                    numeroElementos--;
                                    selectParroquias
                                        .remove(numeroElementos);
                                }

                                opcion.text = '-- Seleccione --';
                                opcion.value = ''
                                selectParroquias.add(opcion,
                                    null);


                                if ($("#provinciaTrabajo").val() == '') {
                                    var selectCiudades = document
                                        .getElementById("ciudadTrabajo");
                                    var opcion = document
                                        .createElement('option');

                                    var numeroElementos = selectCiudades.length;
                                    while (numeroElementos > 0) {
                                        numeroElementos--;
                                        selectCiudades
                                            .remove(numeroElementos);
                                    }

                                    opcion.text = '-- Seleccione --';
                                    opcion.value = ''
                                    selectCiudades.add(opcion,
                                        null);
                                } else {
                                    $idCiudad = this.value;
                                    $
                                        .get(
                                            baseUrl + "/funcionesAjax/getCiudades",
                                            {
                                                id : $idCiudad
                                            },
                                            function(data) {
                                                //Cuantos subestados vinieron
                                                var tamCiudades = data[0].length;
                                                //Recupero el select de los subestados
                                                var selectCiudades = document
                                                    .getElementById("ciudadTrabajo");

                                                var numeroElementos = selectCiudades.length;
                                                while (numeroElementos > 0) {
                                                    numeroElementos--;
                                                    selectCiudades
                                                        .remove(numeroElementos);
                                                }
                                                var opt = document
                                                    .createElement('option');
                                                opt.text = '-- Seleccione --';
                                                opt.value = '';
                                                selectCiudades
                                                    .add(
                                                        opt,
                                                        null);
                                                for (i = 0; i < tamCiudades; i++) {
                                                    var opcion = document
                                                        .createElement('option');
                                                    opcion.text = data[1][i];
                                                    opcion.value = data[0][i];
                                                    selectCiudades
                                                        .add(
                                                            opcion,
                                                            null);
                                                }
                                            });
                                }

                            });

                        //PARA LAS CIUDADES
                        $("#ciudad")
                            .change(function(data) {

                                if ($("#ciudad").val() == '') {
                                    var selectParroquias = document
                                        .getElementById("sector");
                                    var opcion = document
                                        .createElement('option');

                                    var numeroElementos = selectParroquias.length;
                                    while (numeroElementos > 0) {
                                        numeroElementos--;
                                        selectParroquias
                                            .remove(numeroElementos);
                                    }

                                    opcion.text = '-- Seleccione --';
                                    opcion.value = ''
                                    selectParroquias.add(opcion,
                                        null);
                                } else {
                                    $idCiudad = this.value;
                                    $
                                        .get(
                                            baseUrl + "/funcionesAjax/getParroquias",
                                            {
                                                id : $idCiudad
                                            },
                                            function(data) {
                                                //Cuantos subestados vinieron
                                                var tamParroquias = data[0].length;
                                                //Recupero el select de los subestados
                                                var selectParroquias = document
                                                    .getElementById("sector");

                                                var numeroElementos = selectParroquias.length;
                                                while (numeroElementos > 0) {
                                                    numeroElementos--;
                                                    selectParroquias
                                                        .remove(numeroElementos);
                                                }
                                                var opt = document
                                                    .createElement('option');
                                                opt.text = '-- Seleccione --';
                                                opt.value = '';
                                                selectParroquias
                                                    .add(
                                                        opt,
                                                        null);
                                                for (i = 0; i < tamParroquias; i++) {
                                                    var opcion = document
                                                        .createElement('option');
                                                    opcion.text = data[1][i];
                                                    opcion.value = data[0][i];
                                                    selectParroquias
                                                        .add(
                                                            opcion,
                                                            null);
                                                }
                                            });
                                }

                            });

                        $("#ciudadTrabajo")
                            .change(function(data) {

                                if ($("#ciudadTrabajo").val() == '') {
                                    var selectParroquias = document
                                        .getElementById("sectorTrabajo");
                                    var opcion = document
                                        .createElement('option');

                                    var numeroElementos = selectParroquias.length;
                                    while (numeroElementos > 0) {
                                        numeroElementos--;
                                        selectParroquias
                                            .remove(numeroElementos);
                                    }

                                    opcion.text = '-- Seleccione --';
                                    opcion.value = ''
                                    selectParroquias.add(opcion,
                                        null);
                                } else {
                                    $idCiudad = this.value;
                                    $
                                        .get(
                                            baseUrl + "/funcionesAjax/getParroquias",
                                            {
                                                id : $idCiudad
                                            },
                                            function(data) {
                                                //Cuantos subestados vinieron
                                                var tamParroquias = data[0].length;
                                                //Recupero el select de los subestados
                                                var selectParroquias = document
                                                    .getElementById("sectorTrabajo");

                                                var numeroElementos = selectParroquias.length;
                                                while (numeroElementos > 0) {
                                                    numeroElementos--;
                                                    selectParroquias
                                                        .remove(numeroElementos);
                                                }
                                                var opt = document
                                                    .createElement('option');
                                                opt.text = '-- Seleccione --';
                                                opt.value = '';
                                                selectParroquias
                                                    .add(
                                                        opt,
                                                        null);
                                                for (i = 0; i < tamParroquias; i++) {
                                                    var opcion = document
                                                        .createElement('option');
                                                    opcion.text = data[1][i];
                                                    opcion.value = data[0][i];
                                                    selectParroquias
                                                        .add(
                                                            opcion,
                                                            null);
                                                }
                                            });
                                }

                            });



						//Validaciones Cesar
                        $('#telefonoContacto').on('keypress', function(e) {
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

                        $('#telefonoTrabajoGestion').on('keypress', function(e) {
                            if(checkIfNumberNoSpace(e.which, e)==0){
                                return false;
                            }else{
                                return;
                            }
                        });

                        $('#personaContacto').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#callePrincipal').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#nomenclatura').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#calleSecundaria').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#edificio').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#referencia').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });


                        $('#callePrincipalTrabajo').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#nomenclaturaTrabajo').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#calleSecundariaTrabajo').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#edificioTrabajo').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#referenciaTrabajo').on('keypress', function(e) {

                            if(validateEnter(e.which, e)==0){
                                return false;
                            }else{
                                return true;
                            }

                            $('#'+ this.id).val(removeDoubleSpace($('#'+ this.id).val()));
                            $('#'+ this.id).val(isValidString($('#'+ this.id).val()));
                        });

                        $('#observaciones').on('keypress', function(e) {

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
