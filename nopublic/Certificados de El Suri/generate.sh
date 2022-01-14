#!/bin/bash
keytool -genkey -alias elsuri -keystore serverStore.cer -keyalg rsa -keysize 2048
keytool -export -rfc -alias elsuri -keystore serverStore.cer -file server.cer
keytool -import -alias trustservercert -file server.cer -keystore clientStore.cer
