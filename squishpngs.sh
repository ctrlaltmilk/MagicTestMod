#!/bin/sh

for f in $(find "./src/main/resources" -name "*.png"); do
    pngcrush -rem alla -ow "$f"
done
