const dropLists = document.querySelectorAll(".drop-list select");
const getButton = document.querySelector("form button");
const fromCurrency = document.querySelector(".from select");
const toCurrency = document.querySelector(".to select");


dropLists.forEach((dropList, index) => {
    for (let currency_code in country_code) {
        let selected;
        if (index == 0) {
            selected = currency_code == "USD" ? "selected" : "";
        } else if (index == 1) {
            selected = currency_code == "NPR" ? "selected" : "";
        }
        let countryFlag = country_code[currency_code].toLowerCase();
        let optionTag = `<option value="${currency_code}" ${selected}>${currency_code}</option>`;
        dropList.insertAdjacentHTML("beforeend", optionTag);
    }

    dropList.addEventListener("change", e => {
        loadFlag(e.target);
    });

    loadFlag(dropList);
});

function loadFlag(element) {
    const selectedCurrency = element.value;
    const flagCode = country_code[selectedCurrency];

    if (flagCode) {
        let imgTag = element.parentElement.querySelector("img");
        if (imgTag) {
            imgTag.src = `https://flagcdn.com/w40/${flagCode.toLowerCase()}.png`;
        }
    }
}

window.addEventListener("onload", () => {
    getExchangeRate();
});

getButton.addEventListener("click", e => {
    e.preventDefault();
    getExchangeRate();
});

const exchangeIcon = document.querySelector(".drop-list .icon");
exchangeIcon.addEventListener("click", ()=>{
    let tempCode = fromCurrency.value;
    fromCurrency.value = toCurrency.value;
    toCurrency.value = tempCode;
    loadFlag(fromCurrency);
    loadFlag(toCurrency);
    getExchangeRate();
});

function getExchangeRate() {
    const amount = document.querySelector(".amount input");
    exchangeRateTxt = document.querySelector(".exchange-rate")
    let amountVal = amount.value;
    if (amountVal == "" || amountVal == "0") {
        amount.value = "1";
        amountVal = 1;
    }

    exchangeRateTxt.innerText = "Getting exchange rate...";
    let apiKey = "5729386b13a776363b833efd";
    let url = ` https://v6.exchangerate-api.com/v6/${apiKey}/latest/${fromCurrency.value}`;
    fetch(url).then(response => response.json()).then(result => {
        let exchangeRate = result.conversion_rates[toCurrency.value];
        let totalExchangeRate = (amountVal * exchangeRate).toFixed(2);
        const exchangeRateTxt = document.querySelector(".exchange-rate");
        exchangeRateTxt.innerText = `${amountVal} ${fromCurrency.value} = ${totalExchangeRate} ${toCurrency.value}`;
    }).catch(() =>{
        exchangeRateTxt.innerText = "something went wrong";
    })

}
