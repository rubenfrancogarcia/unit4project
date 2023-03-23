const baseUrl = '/api/v1/notes';
const logoutButton = document.getElementById( 'logout-btn' );
document.getElementById( 'logout-btn' ).addEventListener( 'click', logout );
const modal = document.getElementById( 'note-edit-modal' );
logoutButton.addEventListener( "click", logout );
let loading = true;
let username = localStorage.getItem('username')
if(localStorage.getItem('username') == null){
    localStorage.setItem('username', 'test')
    username = localStorage.getItem('username')
}
console.log(username)
document.getElementById('welcome-prompt').innerHTML = `<p> Hello ${username}`
function logout ( e )
{
    e.preventDefault();
    localStorage.clear();
    window.location.assign( "./login.html" );
}
/* to add note*/

const addNoteForm = document.getElementById( 'note-form-container' );
const addNoteBody = document.getElementById( 'note-input' );
const submitNewNoteBtn = document.getElementById( 'submit-note-button' );

const headers = {
    'Content-Type': 'application/json'
};
//search function
//todo
async function addNote ( )
{
    let userName = localStorage.getItem('username')
    let body = {
        body: document.getElementById('note-input').value
    };
    const response = await fetch( `${baseUrl}/addNote?userName=${userName}`, {
        method: 'POST',
        body: JSON.stringify( body ),
        headers: headers
    } )
    .catch( err => console.error( err.message ) );

}
/*to delete note*/

async function deleteNote ( element )
{
    let noteId = element.parentElement.getAttribute( "data-note-id" );
    const response = await fetch( `${ baseUrl }/deleteNote?noteId=${ noteId }`, {
        method: 'DELETE',
        headers: headers
    } ).catch( error => console.error( error ) );
    if ( response.status == 200 )
    {
        document.getElementById(`${noteId}-id`).parentElement.remove()
    }
}
function storeNoteId ( element )
{
    let noteId = element.parentElement.getAttribute( "data-note-id" );
    let noteBody = document.getElementById( `${ noteId }-id-body` ).innerText;
    console.log( noteId + " number is stored" );
    localStorage.setItem( 'currentNoteBody', noteBody );
    localStorage.setItem( 'currentNoteId', noteId );
    document.getElementById( 'edit-note-body-field' ).value = noteBody;
}


/*to update note*/
async function editNote ()
{
    let noteId = localStorage.getItem( 'currentNoteId' );
    let body = localStorage.getItem( 'currentNoteBody' );
    let text = document.getElementById( 'edit-note-body-field' ).value;

    const response = await fetch( `${ baseUrl }/updateNote?noteId=${ noteId }`, {
        body: text,
        method: 'Put',
        headers: headers
    } ).catch( error => console.error( error ) );
    if ( response.status == 200 )
    {
        console.log( "updated table" );
        localStorage.removeItem( 'currentNoteId' );
        localStorage.removeItem( 'currentNoteBody' );
        location.reload();
    }
}


/*to get all notes by logged in user*/
async function getAllNotes ()
{
    let userName = localStorage.getItem( 'username' );
    userName = "test";
    await fetch( `${ baseUrl }/findAllNotesBy/${ userName }` )
        .then( data => data.json() )
        .then( data =>
        {
            setTimeout(()=> {
                populateUserNotes( data )
                loading = false;
                if ( loading == false )
                {
                    document.getElementById( 'loader' ).style.display = "none";
                }
            } , 3000 );
           
        } )
        .catch( error => console.error( error ) );


}

function populateUserNotes ( arr )
{
    arr.forEach( obj =>
    {
        console.log( obj );
        let noteRow = document.createElement( "tr" );
        noteRow.innerHTML = `
    <td id="${ obj.id }-id" >${ obj.id }</td><td id="${ obj.id }-id-body" ><p>${ obj.body }</p></td><td data-note-id="${ obj.id }"><button onclick="storeNoteId(this)" type="button" data-bs-toggle="modal" data-bs-target="#editNoteModal" > edit</button><button onClick="deleteNote(this)">delete</button></td
    `;
        document.getElementById( 'notes-table-body' ).append( noteRow );

    } );
}


const deleteNoteBtn = document.getElementById( 'delete-note-btn' );
window.onload = getAllNotes();