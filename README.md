# whatsapp-database-remover

[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/kevalpatel2106/UserAwareVideoView) [![API](https://img.shields.io/badge/API-15%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=15)

##What is this application for?
- WhatsApp creates a back-up of the existing messages every day at around 2 a.m. It stores that into the phone's external storage under /Databases folder. WhatsApp keeps databases for last 7 days. All those databases are redundant and not required while restoring the WhatsApp from backup. Sometimes those redundant databases users over a half GB in your internal storage. For the phones with less internal storage (e.g. Motorola Moto E 1st generation which has 4 GB of the internal storage), this is a huge problem.
- This application is designed to delete all those redundant backup databases from the internal storage periodically. This checks and deletes all the redundant databases twice a day when your phone is charging. So, this won't drain your battery too.
- WhatsApp-Database-Remover keeps **msgstore.db** file as this file is important while restoring the messages from backup if you delete all the messages from your WhatsApp accidentally.

##Where can I find application?
- [![Get it on Google Play](/assets/google-play-badge.png)](https://play.google.com/store/apps/details?id=com.kevalpatel.whatsappdatabaseremover)
- Install the application. (Make sure you enabled to allow external sources for the application installation in Settings.)
- Open the application and grant the required permission if asked.
- That's it. You saved you memory. :-)

##What if I find an issue?
- You can report any issue you found [here](https://github.com/kevalpatel2106/whatsapp-database-remover/issues).
- Make sure you provide detailed description of your.

##Contribute:
####Simple 3 step to contribute into this repo:

1. Fork the project. 
2. Make required changes and commit. 
3. Generate pull request. Mention all the required description regarding changes you made.

## Questions
[@Kevalonly77](https://twitter.com/Kevalonly77)
