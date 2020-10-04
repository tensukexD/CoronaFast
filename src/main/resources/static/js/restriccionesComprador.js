

var estadoEmail = false;
var arregloCompradores = [];

var url = "http://localhost:8080/revisarCompradores/compradores/mostrar";

var xhr = new XMLHttpRequest();

var alertaEmail = document.getElementById("alertaEmail");
var botonCrear = document.getElementById("btnComprador");

function btnActivo(){
    if(estadoEmail == true){
        botonCrear.disabled = false;
    }else{
        botonCrear.disabled = true;
    }
}

function obtenerCompradores(){
    xhr.onload = function(){
        var arregloJSON = JSON.parse(xhr.response);
        arregloCompradores = arregloJSON;
        console.log(arregloCompradores[0].email);
    }
    xhr.open('GET', url,true);
    xhr.send();
}

var email = document.getElementById("email");

email.addEventListener("input",function(){
    if(email.value==""){
        alertaEmail.innerHTML = "<strong>Deber llenar este campo obligatoriamente</strong>";
        alertaEmail.style.display = "block";
        estadoEmail = false;
        btnActivo();
    }else{
        alertaEmail.style.display = "none";
        if(email.value.length>50){
            alertaEmail.innerHTML = "<strong>No puedes pasar los 50 caracteres</strong>";
            alertaEmail.style.display = "block";
            estadoEmail = false;
            btnActivo();
        }else{
            estadoEmail = true;
            alertaEmail.style.display = "none";
            if(arregloCompradores != []){
                for(var i=0;i<arregloCompradores.length;i++){
                    if(email.value==arregloCompradores[i].email){
                        alertaEmail.innerHTML = "<strong>La entidad ya se ha creado</strong>";
                        alertaEmail.style.display = "block";
                        estadoEmail = false;
                    }
                }
            }
            btnActivo();
        }
    }
})

window.onload = function(){
    obtenerCompradores();
}
