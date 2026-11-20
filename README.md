# GrapheneOS-wallet-torch
a torch app that identifies itself as a wallet to your phone
stock AOSP does not allow one to set any app to launch when double pressing the power button, the only supported options are camera and wallet apps. Furthermore, the ~~fucking dipshits~~ very nice people over at GrapheneOS found it very fitting to do __absolutely nothing__ about that, even going as far as to comment on the matter.

In any case this can be fixed by creating an app that announces itself as either a camera or a wallet app, setting it as default, then going to Settings>System>Gestures>Double Press Power Button> choose Camera or Wallet
In my case I do need my default camera app to be an actual camera, but I do not use a digital wallet, so I chose to create a "wallet" app that just turns on the flashlight. YMMV

## Build From Source
I just used Github Actions: fork this repo, go to the Actions tab, click "Build APK", then "run workflow", wait for the build to complete (there's a button to refresh it, by default it's set to 30 secs, you can refresh manually whenever you want tho), click on said build, and install the file provided in the "Artifacts" tab, and BOOM, you got the file built from source.
If you do wish to compile it on your own machine, good luck because I'm not an Android developer and I do not know what the fuck any of this shit does.

__This was entirely built by an AI agent, do with that information what you want__
