# voteroid

An application for supporting audience interaction with reveal.js presentations.

To run the examples, clone or download the project, then:
- start the server (runs on `localhost:8080`) with `./mvnw spring-boot:run`.
- open the presentation: 
    - download `reveal.js-4.5.0` (from https://github.com/hakimel/reveal.js/releases) 
    - copy the `.adoc` and `-docinfo-footer.html` file(s) from this repository's `examples` directory to the root of the `reveal.js` presentation
    - copy the file `reveald3.js` (from https://github.com/gcalmettes/reveal.js-d3) into the `plugins` directory of the `reveal.js` presentation
    - run Asciidoctor reveal.js (download from https://github.com/asciidoctor/asciidoctor-reveal.js/releases) on the `.adoc` file(s)
    - open the resulting `html` file.
- open `client/phoneClient.html`

Version history:
- 0.0.1 Initial commit
- 0.0.2 Provide column labels (for one slide only)