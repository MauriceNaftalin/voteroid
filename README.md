# voteroid

An application for supporting audience interaction with reveal.js presentations.

To run the example (`examples/presentation-local.adoc`), clone or download the project, then:
- start the server (runs on `localhost:8080`) with `./mvnw spring-boot:run`.
- open the presentation: 
    - download `reveal.js-4.5.0` (from https://github.com/hakimel/reveal.js/archive/refs/tags/4.5.0.zip or https://github.com/hakimel/reveal.js/archive/refs/tags/4.5.0.tar.gz) and unpack it;
    - download asciidoctor-revealjs-<*yourOS*> (from https://github.com/asciidoctor/asciidoctor-reveal.js/releases) 
    - run `java com.voteroid.util.DeployPresentation` `examples/presentation-local.adoc` *`path/to/asciidoctor-revealjs-<yourOS>`* *`path/to/reveal.js-4.5.0`*
    - open *`path/to/reveal.js-4.5.0*/presentation-local.html`* locally
- open `http://localhost:8080/client.html` (on a phone simulator if possible)

Version history:
- 0.0.1 Initial commit
- 0.0.2 Provide column labels (for one slide only)
- 0.0.3 Multiple questions – client aware, but without UI changes
- 0.0.4 Client reflects slide name and answer labels
- 0.0.5 Client shows voting chart
- 0.0.6 Simplify .adoc and automate deployment
