const baseUrl = 'http:localhost:8080/api/v1/notes';
const logoutButton = document.getElementById('logout-btn')

function logout(e){
    e.preventDefault();
    localStorage.clear(); 
}

logoutButton.addEventListener("click", logout)
/* to add note*/

const addNoteForm = document.getElementById('note-form-container')
const addNoteBody = document.getElementById('note-input')

const submitNewNoteBtn = document.getElementById('submit-note-button')
const headers = {
    'Content-Type': 'application/json'
};
async function addNote(e){
    let body = {
        body: addNoteBody
    }
    const response = await fetch('${baseUrl}/addNote', {
        method:'POST',
        body: JSON.stringify(bodyObj),
        headers: headers
    }).catch(err => console.error(err.message))

    const responeArr = await response.json()
    if(response.status == 200){
        console.log("note added")
    }
}
submitNewNoteBtn.addEventListener('submit', addNote)

/*to delete note*/
const deleteNoteBtn = document.getElementById('delete-note-btn')

async function deleteNote(e,noteId){
    e.preventDefault();
    const response = await fetch('${baseUrl}/deleteNote?noteId=${noteId}', {
        method: 'DELETE',
        headers: headers 
    }).catch(err => console.error(err.message)).json()

   if(response.status == 200){
    console.log("note is deleted")
    notes.delete(notes.get(noteId))
    //update ui based on new map
   }
}
/*to update note*/

/*to get all notes by logged in user*/
let notes = new Map(); 
function renderNotesByUser(userId){

}

async function getAllNotes(e){
    let userId = localStorage.getItem('username')
    e.preventDefault();
    const response = await fetch('${baseUrl}/findAllNotesBy/${userId}')
}