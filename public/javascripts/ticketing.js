function search() {
	frmHome.action = "searchTicket";
}
function createTicket() {
	frmHome.action = "createTicket";
}
function saveTicket() {
	frmTicket.action = "saveTicket";
}

function addComment() {
	frmTicket.action = "addComment";
}
function clearAll() {
	frmTicket.getElementById("ticketId").value ="";
	frmTicket.getElementById("ticketTitle").value ="";
	frmTicket.getElementById("description").value ="";
	frmTicket.getElementById("assignedTo").value ="";
	frmTicket.getElementById("createdBy").value ="";
	frmTicket.getElementById("selectStatus").value ="";
}