function convertCurrency(event) {
    event.preventDefault();  // Prevent the default form submission

    const fromCurrency = document.getElementById("fromCurrency").value;
    const toCurrency = document.getElementById("toCurrency").value;
    const amount = document.getElementById("amount").value;

    const data = new FormData();
    data.append("fromCurrency", fromCurrency);
    data.append("toCurrency", toCurrency);
    data.append("amount", amount);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/convert", true);
    xhr.setRequestHeader("Accept", "text/html");

    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById("result").innerHTML = xhr.responseText;
        } else {
            document.getElementById("result").innerHTML = "Error: Unable to process the request.";
        }
    };

    xhr.send(data);
}
