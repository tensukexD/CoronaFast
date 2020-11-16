

var estadoEmail = false;
var arregloCompradores = [];
var arregloVendedores = [];

var url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/mostrar";
var url2 = "http://coronafast.herokuapp.com/revisarVendedores/vendedores/mostrar/";

var xhr = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();

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

function obtenerVendedores(){
    xhr2.onload = function(){
        var arregloJSON = JSON.parse(xhr2.response);
        arregloVendedores = arregloJSON;
        console.log(arregloVendedores[0].email);
    }
    xhr2.open('GET', url2,true);
    xhr2.send();
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
            if(arregloCompradores != [] || arregloVendedores != []){
                for(var i=0;i<arregloCompradores.length;i++){
                    if(email.value==arregloCompradores[i].email){
                        alertaEmail.innerHTML = "<strong>La entidad ya se ha creado</strong>";
                        alertaEmail.style.display = "block";
                        estadoEmail = false;
                    }
                }
                for(var j=0;j<arregloVendedores.length;j++){
                    if(email.value==arregloVendedores[j].email){
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
    obtenerVendedores();
}
