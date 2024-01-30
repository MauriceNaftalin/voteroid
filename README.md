# voteroid

An application for supporting audience interaction with reveal.js presentations.

To run the example, clone or download the project, then:
- start the server (runs on `localhost:8080`) with `./mvnw spring-boot:run`.
- open the presentation: 
    - download `reveal.js-4.5.0` (from https://github.com/hakimel/reveal.js/releases) 
    - copy `presentation-local.adoc` and `presentation-local-docinfo-footer.html` from this repository's `examples` directory to the root of the `reveal.js` presentation
    - run Asciidoctor reveal.js (download from https://github.com/asciidoctor/asciidoctor-reveal.js/releases) on  `presentation-local.adoc` 
    - open `presentation-local.html` locally
- open `http://localhost:8080/phoneClient.html`

Version history:
- 0.0.1 Initial commit
- 0.0.2 Provide column labels (for one slide only)
- 0.0.3 Multiple questions – client aware, but without UI changes