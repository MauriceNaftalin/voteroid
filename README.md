# voteroid

An application for supporting audience interaction with reveal.js presentations.

To run the examples, clone or download the project, then:
- start the server (on `localhost:8080`) with `mvnw spring-boot:run`.
- open the presentation: download `reveal.js-4.5.0`, copy the `.adoc` and `-docinfo-footer.html` file(s) to the root, run `asciidoctor-revealjs-macos` on the `.adoc` file(s), open the resulting `html` file.
- open `client/phoneClient.html`

Version history:
- 0.0.1 Initial commit
- 0.0.2 Provide column labels (for one slide only)