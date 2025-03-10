document.getElementById('sortButton').addEventListener('click', () => {
    const input = document.getElementById('numbers').value.trim();
    const numbers = input.split(',').map(Number);

    fetch('/sort', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ array: numbers })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('results').innerHTML = `
            <strong>Unsorted Array:</strong> ${data.unsorted} <br>
            <strong>Array Size:</strong> ${data.size} <br>
            <strong>Sorting Algorithm:</strong> ${data.algorithm} <br>
            <strong>Sorted Array:</strong> ${data.sorted}
        `;
        alert("Sorted Array saved to MongoDB Atlas!");
    })
    .catch(error => {
        console.error("Error:", error);
    });
});
