#!/bin/bash
cd /home/kavia/workspace/code-generation/simple-notes-manager-209756-209801/notes_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

