var Gcm = require('node-gcm')
var TelegramApi = require('node-telegram-bot-api')
var Utils = require('util')

var mwbot = new TelegramApi('166836510:AAERvicJuCtZguWl671ppc7jT1XWaEteEuk', { polling: true })
var sender = new Gcm.Sender('AIzaSyCOZNyZ-bGFNZhysWo30SYH0lEXThBDU04')

mwbot.getMe().then(function (me) {
    console.log('\n Hi my name is %s! \n', me.username)
})

mwbot.onText(/\/sendpush/, function (msg) {
    var chatId = msg.chat.id
    var text = msg.text.split(' ')[1]

    if (text == undefined)
        mwbot.sendMessage(chatId, "Comando invalido \n/sendPush <suaMensagemAqui>", { reply_to_message_id: msg.message_id })
    else
        sendPush(text, function (response) {
            console.log("Success!\n" + Utils.inspect(response))
            mwbot.sendMessage(chatId, "Success!\n" + Utils.inspect(response), { reply_to_message_id: msg.message_id })
        }, function (reason) {
            console.log("Error \n" + Utils.inspect(reason))
            mwbot.sendMessage(chatId, "Error \n" + Utils.inspect(reason), { reply_to_message_id: msg.message_id })
        })

})

function sendPush(txt, success, error) {
    var message = new Gcm.Message()
    message.addData('message', txt)

    sender.sendNoRetry(message, { topic: '/topics/global' }, function (err, result) {
        if (err)
            error(err)
        else
            success(result)
    })
}
