// Change table color
document.getElementById("table").onclick = () => {
    const table = document.getElementById("table");
    if (table.style.background === "yellow") {
        table.style.background = "grey";
    } else {
        table.style.background = "yellow";
    }
};

// Function to add a row
function addRow() {
    const table = document.getElementById("table");
    const newRow = table.insertRow(); 

    
    const nameCell = newRow.insertCell(0);
    const jobCell = newRow.insertCell(1);
    const ageCell = newRow.insertCell(2);

    nameCell.innerHTML = "Neuer Name";
    jobCell.innerHTML = "Neuer Beruf";
    ageCell.innerHTML = "Neues Alter";
    
}

// Function to delete the last row
function deleteTableRow() {
    const table = document.getElementById("table");
    if (table.rows.length > 1) { 
        table.deleteRow(table.rows.length - 1); 
    } else {
        alert("Keine Zeilen mehr zum Löschen!");
    }
}

//add event listeners for the buttons
document.getElementById("btn1").onclick = addRow;
document.getElementById("btn2").onclick = deleteTableRow;