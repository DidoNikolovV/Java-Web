let reloadBooksButton = document.getElementById('reloadBooks');

reloadBooksButton.addEventListener('click', reloadBooks);

function reloadBooks() {

    let booksContainer = document.getElementById('books-container');
    booksContainer.innerHTML = '';


    fetch('http://localhost:8080/api/books')
        .then(res => res.json())
        .then(json => json.forEach(book => {
            let bookRow = document.createElement('tr');

            let titleCol = document.createElement('td');
            let authorCol = document.createElement('td');
            let isbnCol = document.createElement('td');
            let actionCol = document.createElement('td');

            titleCol.textContent = book.title;
            authorCol.textContent = book.author.name;

            let deleteBtn = document.createElement('button');
            deleteBtn.innerHTML = 'Delete';
            deleteBtn.dataset.id = book.id;
            deleteBtn.addEventListener('click', deleteBook);

            actionCol.append(deleteBtn);

            bookRow.appendChild(titleCol);
            bookRow.appendChild(authorCol);
            bookRow.appendChild(isbnCol);
            bookRow.appendChild(actionCol);

            booksContainer.append(bookRow);
        }));
}

function deleteBook(e) {
    let bookId = e.target.dataset.id;
    let requestOptions = {
        method: 'DELETE'
    }

    fetch(`http://localhost:8080/api/books/${bookId}`, requestOptions)
        .then(_ => reloadBooks())
        .catch(error => console.log(error.message));
}