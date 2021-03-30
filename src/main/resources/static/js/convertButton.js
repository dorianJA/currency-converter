$('#button_convert').click(async function (e) {
    let convertRequest = {
        from: $('#fromCharCode').val(),
        to: $('#toCharCode').val(),
        value: $('#amount').val(),
    };

    let response = await fetch('https://small-currency-converter.herokuapp.com/convert', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
        body: JSON.stringify(convertRequest)
    });
    if(response.ok){
        let result = await response.json();
        document.getElementById('currency_result').value = result;
        console.log("OK!!!");
    };

    if(response.status==400){
        alert("Укажите корректную сумму!")
        // let result = await response.json();
        // let msg = result.message;
        // for (let i = 0; i < result.length; i++) {
        //     let errorMessage = result[i]['defaultMessage'];
        //     alert(errorMessage);
        // }
        // if(msg!=null){
        //     alert(msg);
        // }
    }

});