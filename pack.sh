#!/bin/bash
suffix=$(date +%Y)$(date +%m)$(date +%d)
tar cvzf elsuri-0.9.7-$suffix-src.tar.gz elsuri-0.9.7
tar cvzf elsuri-0.9.7-$suffix-src-nodeps.tar.gz elsuri-0.9.7 --exclude=elsuri-0.9.7/contrib/3rdParty
#tar cvjf elsuri-0.9.7-$suffix-src.tar.bz elsuri-0.9.7
echo Listo...
