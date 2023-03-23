const registerForm = document.getElementById( 'login-form' );
const registerUsername = document.getElementById( 'login-username' );
const registerPassword = document.getElementById( 'login-password' );
let incorrect = null; 
const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = '/api/v1/users';

async function handleSubmit ( e )
{
    let username = registerUsername.value
    e.preventDefault();
    let bodyObj = {
        username: username,
        password: registerPassword.value
    };


    let response = await fetch( `${baseUrl}/login`, {
        method: 'POST',
        body: JSON.stringify( bodyObj ),
        headers: headers
    })
    .then(data => data.json())
    .catch( err => console.error( err.message ) )


    if ( response[0] === "http://localhost:8080/login.html" )
    {
        window.location.assign("./home.html");
        window.localStorage.setItem("username", username)
    }else{
        if(incorrect == true){
            return; 
        }
        incorrect = true; 
        let p = document.createElement('p') // is a node
        p.innerHTML = 'Incorrect. Try again'
        document.body.appendChild(p)
    }
}

registerForm.addEventListener("submit", handleSubmit)



