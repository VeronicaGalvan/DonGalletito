//------------------------------------------------------------------------------
// Función para cargar la tabla con todos los libros
function CargarTablaLibros() {
    let ruta = "http://localhost:8080/DonGalleto/api/receta/getall";

    fetch(ruta)
        .then(response => response.json())
        .then(data => {
            // Guardar los datos globalmente
            let tbody = document.querySelector("#tablarecetas tbody");
            tbody.innerHTML = '';  // Limpiar la tabla antes de agregar nuevas filas

            // Llenar la tabla con las recetas
            data.forEach(receta => {
                let row = tbody.insertRow();  // Crear una nueva fila
                let cellNombre = row.insertCell(0);  // Celda para el nombre
                let cellReceta = row.insertCell(1);  // Celda para la receta

                cellNombre.textContent = receta.nombreReceta;  // Nombre de la galleta
                
                // Agregar el botón en la columna de la receta
                let button = document.createElement('button');
                button.textContent = 'Ver receta';
                button.onclick = function() {
                    previsualizarPDF(receta.receta);  // Llamar a la función para ver el PDF
                };
                cellReceta.appendChild(button);  // Añadir el botón en la columna de receta
            });
        })
        .catch(error => console.error("Error al cargar la tabla: " + error));
}

//------------------------------------------------------------------------------
// Función para previsualizar el PDF cargado
function previsualizarPDF(base64PDF) {
    let iframe = document.createElement('iframe');
    iframe.src = 'data:application/pdf;base64,' + base64PDF;
    iframe.width = "50%";
    iframe.height = "300px";
    iframe.style.border = "none";

    let divPreview = document.getElementById("divPrevisualizacionPDF");
    divPreview.style.display = 'block';
    divPreview.innerHTML = '';  // Limpiar cualquier contenido previo

    let closeButton = document.createElement('button');
    closeButton.textContent = 'Cerrar';
    closeButton.addEventListener('click', function () {
        divPreview.style.display = 'none';  // Ocultar la previsualización
        divPreview.innerHTML = '';  // Limpiar contenido de la previsualización
    });

    divPreview.appendChild(closeButton);  // Botón para cerrar
    divPreview.appendChild(iframe);  // Insertar el iframe para previsualizar el PDF
}

//------------------------------------------------------------------------------
// Función para insertar un nuevo libro
function insertarLibro() {
    let ruta = "http://localhost:8080/DonGalleto/api/receta/insert";
    let _datos = {
        nombreReceta: document.getElementById("nombreReceta").value
    };

    // Validar los datos
    if (!validardatosLibro())
        return;

    // Procesar el archivo PDF
    const archivoInput = document.getElementById("receta").files[0];
    let receta = null;

    if (archivoInput) {
        const reader = new FileReader();
        reader.onloadend = function () {
            receta = reader.result.split(',')[1];  // Obtener solo la parte base64

            // Enviar los datos una vez que el PDF está cargado
            enviarDatosLibro(_datos, receta);
        };
        reader.readAsDataURL(archivoInput);
    } else {
        alert("Por favor, selecciona un archivo PDF.");
    }
}

//------------------------------------------------------------------------------
// Función para enviar datos del libro al servidor
function enviarDatosLibro(datosLibro, receta) {
    datosLibro.receta = receta; // Añadir el PDF a los datos

    const queryString = new URLSearchParams(datosLibro).toString();
    fetch('http://localhost:8080/DonGalleto/api/receta/insert', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: queryString
    })
        .then(response => response.json())
        .then(() => {
            limpiarLibros(); // Limpiar los campos
            CargarTablaLibros(); // Recargar la tabla de libros
        })
        .catch(error => console.error("Error al insertar el libro: " + error));
}

//------------------------------------------------------------------------------
// Función para limpiar todos los campos del formulario de libros
function limpiarLibros() {
    document.querySelectorAll("input").forEach(input => input.value = "");
    document.getElementById("receta").value = "";
    document.getElementById("nombreReceta").disabled = true;
    CargarTablaLibros(); // Recargar tabla después de limpiar
}

//------------------------------------------------------------------------------
// Función para validar datos del libro
function validardatosLibro() {
    const campos = ["nombreReceta"];
    const vacio = campos.some(campo => document.getElementById(campo).value === "");
    if (vacio) {
        alert("Por favor, completa todos los campos.");
        return false;
    }
    return true;
}

//------------------------------------------------------------------------------
// Función para inicializar la tabla de libros al cargar la página
function initLibros() {
    CargarTablaLibros();
}

initLibros(); 