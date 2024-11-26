function iniciar() {
    // Obtener los valores de usuario y contraseña
    let usuario = document.getElementById("usuario").value;
    let contrasena = document.getElementById("contrasena").value;

    // Verificar que los campos no estén vacíos
    if (!usuario || !contrasena) {
        alert("Por favor, ingrese usuario y contraseña.");
        return; // Detener la ejecución si los campos están vacíos
    }

    // Construir la URL para la solicitud POST
    let url = "http://localhost:8080/DonGalleto/api/inicioSesion/autenticar";

    // Crear los datos a enviar en la solicitud POST
    let datos = new URLSearchParams();
    datos.append("usuario", usuario);
    datos.append("contrasena", contrasena);

    // Configurar las opciones de la solicitud POST
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: datos
    };

    // Realizar la solicitud POST al servidor
    fetch(url, requestOptions)
            .then(response => {
                if (response.ok) {
                    // Usuario autenticado correctamente
                    alert("Inicio de sesión exitoso");
                    window.location.href = "../DonGalleto/modulos/recetas.html";
                } else if (response.status === 401) {
                    alert("Usuario o contraseña incorrectos");
                } else {
                    return response.text().then(text => {
                        console.error("Error del servidor:", text);
                        alert("Error al iniciar sesión. Intenta nuevamente.");
                    });
                }
            })
            .catch(error => {
                console.error("Error al realizar la solicitud:", error);
                alert("Error al conectarse al servidor. Verifica la conexión.");
            });
}
