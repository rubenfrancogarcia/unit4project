const registerForm = document.getElementById( 'login-form' );
const registerUsername = document.getElementById( 'login-username' );
const registerPassword = document.getElementById( 'login-password' );

const headers = {
    'Content-Type': 'application/json'
};

const baseUrl = '/api/v1/users';

async function handleSubmit ( e )
{
    e.preventDefault();
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    };


    const response = await fetch( `${baseUrl}/login`, {
        method: 'POST',
        body: JSON.stringify( bodyObj ),
        headers: headers
    }).catch( err => console.error( err.message ) )

    const responeArr = await response.json()

    if ( response.status == 200 )
    {
        window.location.assign("./home.html");
        window.localStorage.setItem("username", registerUsername)
    }
}

registerForm.addEventListener("submit", handleSubmit)



