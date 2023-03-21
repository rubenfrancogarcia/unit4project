const registerForm = document.getElementById( 'register-form' );
const registerUsername = document.getElementById( 'register-username' );
const registerPassword = document.getElementById( 'register-password' );

const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = 'http:localhost:8080/api/v1/users';

async function handleSubmit ( e )
{
    e.preventDefault();
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    };


    const response = await fetch( '${baseUrl}/register', {
        method: 'POST',
        body: JSON.stringify( bodyObj ),
        headers: headers
    }).catch( err => console.error( err.message ) )

    const responeArr = await response.json()

    if ( response.status == 200 )
    {
        window.location.replace( responseArr[ 0 ] );
    }
}

registerForm.addEventListener("submit", handleSubmit)

