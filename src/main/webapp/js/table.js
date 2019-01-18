$(document).ready(function () {

    var gridArrayData = []; 

    var fields = [
            {"fieldId":"DocumentId"     ,"fieldName":"DNI"                  }, 
            {"fieldId":"Name"           ,"fieldName":"Nombre"               }, 
            {"fieldId":"FirstSurname"   ,"fieldName":"1er apellido"         }, 
            {"fieldId":"SecondSurname"  ,"fieldName":"2do apellido"         }, 
            {"fieldId":"BirthDate"      ,"fieldName":"Fecha de nacimiento"  }, 
            {"fieldId":"BirthCountry"   ,"fieldName":"País de nacimiento" },
            {"fieldId":"AddressCountry" ,"fieldName":"País de residencia"   },
            {"fieldId":"AddressRegion"  ,"fieldName":"Región de residencia" },
            {"fieldId":"AddressCity"    ,"fieldName":"Ciudad de residencia" },
            {"fieldId":"AddressStreet"  ,"fieldName":"Calle"                },
            {"fieldId":"AddressNumber"  ,"fieldName":"Numero"                },
            {"fieldId":"AddressFloor"   ,"fieldName":"Piso"                 },
            {"fieldId":"AddressZipCode" ,"fieldName":"Código postal"        }
    ]; 

    // Configuración de la tabla
    $("#jqGrid").jqGrid({
        colModel: [
            { label: 'DNI',				 name: 'DocumentId', 	width: 80,  formatter: boldText },
			{ label: 'Nombre', 			 name: 'Name', 			width: 100 },
			{ label: '1er apellido', 	 name: 'FirstSurname', 	width: 100 },
			{ label: '2do apellido', 	 name: 'SecondSurname', width: 100 },
			{ label: 'Fecha nacimiento', name: 'BirthDate', 	width: 80 }  
        ],

        viewrecords: true, // show the current page, data rang and total records on the toolbar
        width: 780,
        height: 200,
        rowNum: 10,
		datatype: 'local', // es 'local' porque cargaremos la tabla desde una variable de este javascript
        regional : 'es',
        pager: "#jqGridPager",
        styleUI : 'Bootstrap',  // sin esto, la tabla no tendría estilo
		caption: "Gestión de personas"
    });


    // Configuración de la navegación de la tabla
    $('#jqGrid').navGrid('#jqGridPager',
            // the buttons to appear on the toolbar of the grid
            { 
                edit: false, 
                add: true, 
                del: true, 
                search: true, 
                refresh: true, 
                view: true, 
                position: "left", cloneToTop: false 
            },
            
            {},/*Objeto vacio de Edit*/
            {// options for the Add Dialog
                addCaption: "Añadir persona",
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                },
                onclickSubmit: function (options, delId) {
                    addPerson();
                    return null;
                },
                afterShowForm: configureAddModal,
            },
            
            { // options for the Delete Dailog
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                },
                afterShowForm: configureDelModal,
            },
            {},/*Objeto vacio de Search*/
            {// options for the View Dialog
                editCaption: "The View Dialog",
                template: "",
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText
                }
            });


    function addPerson(){

        var documentId      = $("#add_DocumentId").val(),
            name            = $("#add_Name").val(),
            firstSurname    = $("#add_FirstSurname").val(),
            secondSurname   = $("#add_SecondSurname").val(),
            date            = $("#add_BirthDate").val(),
            country         = $("#add_DocumentId").val(),
            street          = $("#add_AddressStreet").val(),
            number          = $("#add_AddressNumber").val(),
            floor           = $("#add_ddressFloor").val(),
            zipCode         = $("#add_AddressZipCode").val(),
            country         = $("#add_AddressCountry").val(),
            region          = $("#add_AddressRegion").val(),
            city            = $("#add_AddressCity").val();


        var jsonPerson = "{"
            +"\"documentId\":\""        + documentId + "\","
            +"\"name\":\""              + name + "\","
            +"\"firstSurname\":\""      + firstSurname + "\","
            +"\"secondSurname\":\""     + secondSurname + "\","
            +"\"birth\":{"
            +    "\"date\":\""          + date + "\","
            +    "\"country\":\""       + country + "\""
            +"},"
            +"\"address\": {"            
            +    "\"street\":\""        + street + "\","
            +    "\"number\":\""        + number + "\","
            +    "\"floor\":\""         + floor + "\","
            +    "\"zipCode\":\""       + zipCode + "\","
            +    "\"country\":\""       + country + "\","
            +    "\"region\":\""        + region + "\","
            +    "\"city\":\""          + city + "\""
            +"}}";

        

        $.ajax({ 
            headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
            type: "PUT",
            url: "http://localhost:8080/CRUDPersons/rest/persons",
            success: function (result) {
                console.log(result);
                alert("Persona creada correctamente!");
                $("#cData").click(); 
                 $("#refresh_jqGrid").click();
            },
            error: function (result) {
                console.log(result);
            },
            data: jsonPerson,
            datatype: "json",

        });

    }

    // Carga de los datos de la tabla
    fetchGridData();

    function fetchGridData() {
        
		// show loading message
		$("#jqGrid")[0].grid.beginReq();
        $.ajax({
            url: "http://localhost:8080/CRUDPersons/rest/persons",
            success: function (result) {
                for (var i = 0; i < result.persons.length; i++) {
                    var person = result.persons[i];
                    gridArrayData.push({
                        DocumentId:    person.documentId,
                        Name: 		   person.name,
                        FirstSurname:  person.firstSurname,
                        SecondSurname: person.secondSurname,
                        BirthDate: 	   person.birth.date
                    });                           
                }
				// set the new data
				$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData});
				// hide the show message
				$("#jqGrid")[0].grid.endReq();
				// refresh the grid
				$("#jqGrid").trigger('reloadGrid');
            }
        });
    }

    function configureAddModal(){
        // Añadimos al modal el formulario de añadir persona
        $("#TblGrid_jqGrid > tbody").append(generateAddForm());

        // Definimos comportamientos de los selects de paises y regiones

        $("#add_AddressCountry").change(function(){ 
            var idCountry = $(this).val();
            $.ajax({
                url: "http://localhost:8080/CRUDPersons/rest/countries/"+idCountry+"/regions",
                success: function (result) {
                    $("#add_AddressRegion").empty();
                    for (var i = 0; i < result.regions.length; i++) {
                        var itemRegion = result.regions[i];
                        var idRegion   = itemRegion.idRegion;
                        var region     = itemRegion.region;
                        var optionRegion = "<option role=\"option\" value=\""+idRegion+"\">"+region+"</option>";  
                        $("#add_AddressRegion").append(optionRegion); 
                        $("#add_AddressRegion").prop( "disabled", false );                          
                    }
            }});
            $("#add_AddressCity").empty();
            $("#add_AddressCity").prop( "disabled", true );
        });



        $("#add_AddressRegion").change(function(){ 
            var idCountry = $("#add_AddressCountry").val();
            var idRegion = $(this).val();
            $.ajax({
                url: "http://localhost:8080/CRUDPersons/rest/countries/"+idCountry+"/regions/"+idRegion+"/cities",
                success: function (result) {
                    $("#AddressCity").empty();
                    for (var i = 0; i < result.cities.length; i++) {
                        var itemCity = result.cities[i];
                        var idCity   = itemCity.idCity;
                        var city     = itemCity.city;
                        var optionCity = "<option role=\"option\" value=\""+idCity+"\">"+city+"</option>";  
                        $("#add_AddressCity").append(optionCity); 
                        $("#add_AddressCity").prop( "disabled", false );                          
                    }
            }});
        });

  
    }

    function configureDelModal(){
        $("#dData").click(function(){ 
            // Obtener id a eliminar
            var id = "ABC";
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/CRUDPersons/rest/persons/"+id,
                success: function (result) {
                    alert("Persona borrada!");
                 $("#cData").click();
                 $("#refresh_jqGrid").click();
            }});

        });
    }



    function boldText(cellValue, options, rowObject) {
        return "<em>" + cellValue + "</em>";
    };

    

    function generateAddForm(){
            /*var tabla = $('<table></table>').attr("id", "TblGrid_jqGrid").addClass("EditTable").addClass("ui-common-table");*/
            var tbody = $('<tbody></tbody>');

            var options = "";
            $.ajax({
                    async: false,
                    url: "http://localhost:8080/CRUDPersons/rest/countries",
                    success: function (result) {
                        for (var i = 0; i < result.countries.length; i++) {
                            var itemCountry = result.countries[i];
                            var idCountry   = itemCountry.idCountry;
                            var country     = itemCountry.country;
                            options += "<option role=\"option\" value=\""+idCountry+"\">"+country+"</option>";                       
                        }
                    }
                });

            // Campos DNI, nombre, apellidos y fecha de nacimiento
            for(var pos = 0; pos < 5; pos++){
                var fieldId     = fields[pos].fieldId;
                var fieldName   = fields[pos].fieldName;
                
                var tr = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                tr    += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                tr    += "<td class=\"DataTD\"><input type=\"text\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"20\" class=\"FormElement form-control\"></td></tr>";

                tbody.append(tr);   
            }

            // Select pais nacimiento
            var fieldId     = fields[pos].fieldId;
            var fieldName   = fields[pos].fieldName;
            var trBCoun = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                trBCoun += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                trBCoun += "<td class=\"DataTD\"><select role=\"select\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"1\" class=\"FormElement form-control\">";
                trBCoun += options;
                trBCoun += "</select></td></tr>";
            tbody.append(trBCoun); 
            pos++;

            // Select pais residencia
            fieldId     = fields[pos].fieldId;
            fieldName   = fields[pos].fieldName;
            var trACoun = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                trACoun += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                trACoun += "<td class=\"DataTD\"><select role=\"select\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"1\" class=\"FormElement form-control\">";
                trACoun += options;
                trACoun += "</select></td></tr>";
            tbody.append(trACoun); 
            pos++;

            // Select region residencia
            fieldId     = fields[pos].fieldId;
            fieldName   = fields[pos].fieldName;
            var trAReg  = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                trAReg += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                trAReg += "<td class=\"DataTD\"><select disabled role=\"select\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"1\" class=\"FormElement form-control\">";
                trAReg += "<option role=\"option\" value=\"\"></option>";
                trAReg += "</select></td></tr>";
            tbody.append(trAReg); 
            pos++;

            // Select ciudad nacimiento
            fieldId     = fields[pos].fieldId;
            fieldName   = fields[pos].fieldName;
            var trACit = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                trACit += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                trACit += "<td class=\"DataTD\"><select disabled role=\"select\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"1\" class=\"FormElement form-control\">";
                trACit += "<option role=\"option\" value=\"\"></option>";
                trACit += "</select></td></tr>";
            tbody.append(trACit); 
            pos++;

            // Campos calle, piso y código postal
            for(; pos < fields.length; pos++){
                var fieldId     = fields[pos].fieldId;
                var fieldName   = fields[pos].fieldName;
                
                var tr = "<tr rowpos=\""+(pos+1)+"\" class=\"FormData\" id=\"tr_add_"+fieldId+"\">";
                tr    += "<td class=\"CaptionTD\"><label for=\""+fieldId+"\">"+fieldName+"</label></td>";
                tr    += "<td class=\"DataTD\"><input type=\"text\" id=\"add_"+fieldId+"\" name=\""+fieldId+"\" rowid=\"_empty\" size=\"20\" class=\"FormElement form-control\"></td></tr>";

                tbody.append(tr);   
            }

            var tr2 =  "<tr class=\"FormData\" style=\"display:none\"><td class=\"CaptionTD\"></td><td colspan=\"1\" class=\"DataTD\"><input class=\"FormElement\" id=\"id_g\" type=\"text\" name=\"jqGrid_id\" value=\"_empty\"></td></tr>\"";

            tbody.append(tr2);

            /*tabla.append(tbody);*/

            return tbody;
    };

});