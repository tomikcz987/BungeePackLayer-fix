# BungeePackLayer
Another "Fixed" version of BungeePackLayer for Velocity

In the new version of Velocity there is no `ResourcePackRequest`, so the whole class was rewritten and the need to have Protocolize was removed, because of Velocity native listener usage.

This fix prevents resourcepacks from being cleared on the client when the server is switched, so the resourcepack doesn't disappear
