document.getElementById('currency-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Get the form values
    const fromCurrency = document.getElementById('fromCurrency').value;
    const toCurrency = document.getElementById('toCurrency').value;
    const amount = document.getElementById('amount').value;
    
    // Basic validation
    if (!amount || isNaN(amount) || parseFloat(amount) <= 0) {
        alert("Please enter a valid amount.");
        return;
    }

    // Prepare data for submission
    const formData = new FormData();
    formData.append("fromCurrency", fromCurrency);
    formData.append("toCurrency", toCurrency);
    formData.append("amount", amount);

    // Send the data to the server using Fetch API (AJAX)
    fetch('/convert', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        // On successful conversion, store the result and redirect to result.html
        window.localStorage.setItem("conversionResult", data);
        window.location.href = 'result.html';  // Redirect to the result page
    })
    .catch(error => {
        alert("Error during conversion: " + error);
    });
});
