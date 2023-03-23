const registerForm = document.getElementById( 'register-form' );
const registerUsername = document.getElementById( 'register-username' );
const registerPassword = document.getElementById( 'register-password' );
const baseUrl = '/api/v1/users';

const headers = {
    'Content-Type': 'application/json'
};


async function handleSubmit ( e )
{
    e.preventDefault();
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    };


    let response = await fetch(  `${baseUrl}/register`, {
        method: 'POST',
        body: JSON.stringify( bodyObj ),
        headers: headers
    })
    .then(data => data.json())
    .catch( err => console.error( err.message ) )


    if ( response.status == 200 )
    {
        window.location.replace( responseArr[ 0 ] );
    }
}

registerForm.addEventListener("submit", handleSubmit)

