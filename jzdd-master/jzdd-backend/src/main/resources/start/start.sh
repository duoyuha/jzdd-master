#!/bin/bash
java -Djava.security.egd=file:/dev/./urandom -Duser.timezone=GMT+8 -cp .:conf:lib/* cn.phdl.ServiceApplication
