package ru.ft.wol.data.repository

import ru.ft.wol.data.room.entity.ClientData
import ru.ft.wol.domain.entity.Client

fun Client.toClientData() = ClientData(
    id = id,
    name = name,
    address = address,
    macAddress = macAddress
)

fun ClientData.toClient() = Client(
    id = id,
    name = name,
    address = address,
    macAddress = macAddress
)