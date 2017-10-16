$(document).ready(function () {

    loadItems();

    $('#button-table').css({ 'table-layout': 'fixed', 'width': '100%' });
    $('#item-label').css({ 'table-layout': 'fixed', 'width': '100%' });
    $('#item-wrapper').css({ 'padding': '50px', 'height': '100%vh' });

    var moneyIn = 0.00;

    var balanceScreen = $('#balance');
    var balanceInfo = '<h3 id=\'balance\'>';
    balanceInfo += '$' + moneyIn;
    balanceScreen.append(balanceInfo);

    var messageScreen = $('#message');
    var messageInfo = '<h3 id=\'message\'>';
    messageInfo += 'Hello!';
    messageScreen.append(messageInfo);

    var changeScreen = $('#change');
    var changeInfo = '<h3 id=\'change\'>';
    changeScreen.append(changeInfo);


    $('#add-dollar').on('click', function () {
        moneyIn += 1.00;
        moneyIn = Math.round(moneyIn * Math.pow(10, 2)) / Math.pow(10, 2);
        newBalance = '<h3 id=\'balance\'>' + '$' + moneyIn + '</h3>';
        $('#balance').replaceWith(newBalance);
    });

    $('#add-quarter').on('click', function () {
        moneyIn += 0.25;
        moneyIn = Math.round(moneyIn * Math.pow(10, 2)) / Math.pow(10, 2);
        newBalance = '<h3 id=\'balance\'>' + '$' + moneyIn + '</h3>';
        $('#balance').replaceWith(newBalance);
    });

    $('#add-dime').on('click', function () {
        moneyIn += 0.10;
        moneyIn = Math.round(moneyIn * Math.pow(10, 2)) / Math.pow(10, 2);
        newBalance = '<h3 id=\'balance\'>' + '$' + moneyIn + '</h3>';
        $('#balance').replaceWith(newBalance);
    });

    $('#add-nickel').on('click', function () {
        moneyIn += 0.05;
        moneyIn = formatMoney(Math.round(moneyIn * Math.pow(10, 2)) / Math.pow(10, 2));
        newBalance = '<h3 id=\'balance\'>' + '$' + moneyIn + '</h3>';
        $('#balance').replaceWith(newBalance);
    });


    $('#purchase-button').on('click', function () {

        var itemNum = $('#item-screen').val();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + moneyIn + '/item/' + itemNum,
            success: function (change) {

                $.each(change, function (index) {

                    $('#quarters-screen').val(change.quarters);
                    $('#dimes-screen').val(change.dimes);
                    $('#nickels-screen').val(change.nickels);
                    $('#pennies-screen').val(change.pennies);

                });

                moneyIn = 0.00;
                moneyIn = Math.round(moneyIn * Math.pow(10, 2)) / Math.pow(10, 2);
                newMoney = '<h3 id=\'money\'>' + '$' + moneyIn + '</h3>';
                $('#balance').replaceWith(newMoney);
                var newMessage = '<h3 id=\'message\'>Thank You!</h3>';
                $('#message').replaceWith(newMessage);

                loadItems();

            },
            error:
            function (xhr, status, error) {
                var err = JSON.parse(xhr.responseText);
                var message = err.message;
                var errMessage = '<h3 id=\'message\'>' + message + '</h3>';
                $('#message').replaceWith(errMessage);

            }
        });

    });


    $('#change-button').on('click', function () {
        var allPennies = moneyIn * 100;

        var quartersRemain = allPennies % 25;
        var quartersChange = allPennies - quartersRemain;
        var quarters = quartersChange / 25;
        quarters = Math.round(quarters * Math.pow(10, 2)) / Math.pow(10, 2);

        var dimesRemain = quartersRemain % 10;
        var dimesChange = quartersRemain - dimesRemain;
        var dimes = dimesChange / 10;
        dimes = Math.round(dimes * Math.pow(10, 2)) / Math.pow(10, 2);

        var nickelsRemain = dimesRemain % 5;
        var nickelsChange = dimesRemain - nickelsRemain;
        var nickels = nickelsChange / 5;
        nickels = Math.round(nickels * Math.pow(10, 2)) / Math.pow(10, 2);

        var pennies = nickelsRemain;
        pennies = Math.round(pennies * Math.pow(10, 2)) / Math.pow(10, 2);

        $('#quarters-screen').val(quarters);
        $('#dimes-screen').val(dimes);
        $('#nickels-screen').val(nickels);
        $('#pennies-screen').val(pennies);

        moneyIn = 0.00;
        newBalance = '<h3 id=\'balance\'>' + '$' + moneyIn + '</h3>';
        $('#balance').replaceWith(newBalance);

        $('#item-screen').val('Item #');

        newMessage = '<h3 id=\'message\'>Hello!</h3>';
        $('#message').replaceWith(newMessage);

        loadItems();

    });

});

/////////////////////////////////////////////////////////////////////////////

function clearItems() {
    $('#item-wrapper').empty();
};

function selectItem(itemId) {
    $('#item-screen').val(itemId);
};

function formatMoney(money) {
    var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
    });

    formatter.format(money);
    return money;
};

function loadItems() {
    clearItems();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (itemArray) {

            var rowNum = 1;
            var startRow = '<div id="row' + rowNum + '" class="row">';

            var itemWrapper = $('#item-wrapper');
            itemWrapper.append(startRow);

            $.each(itemArray, function (index, item) {

                var itemId = item.id;

                if (itemId % 3 === 0) {

                    var currentRow = $('#row' + rowNum).css({ 'padding': '10px 20px 20px 10px' });
                    var itemInfo = '<div onclick="selectItem(' + itemId + ')" id="item' + itemId + '"class="col-sm-4">';

                    itemInfo += '<button type="button" class="btn btn-default btn-block" id="item-info-button">' + itemId + '<br>'
                        + 'Name: ' + item.name + '<br>'
                        + 'Price: $' + item.price + '<br>'
                        + 'Quantity: ' + item.quantity
                        + '</button>' + '</div>' + '</div>';

                    currentRow.append(itemInfo);
                    rowNum++;
                    var newRow = '<div id="row' + rowNum + '" class="row">';
                    itemWrapper.append(newRow);

                } else {

                    var currentRow = $('#row' + rowNum).css({ 'padding-bottom': '10px 20px 20px 10px' });
                    var itemInfo = '<div onclick="selectItem(' + itemId + ')" id="item' + itemId + '"class="col-sm-4">';


                    itemInfo += '<button type="button" class="btn btn-default btn-block">' + itemId + '<br>'
                        + 'Name: ' + item.name + '<br>'
                        + 'Price: $' + item.price + '<br>'
                        + 'Quantity: ' + item.quantity
                        + '</button>' + '</div>';
                    currentRow.append(itemInfo);

                }

            });

        }

    });
};