<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<br />
<div align="center">
<h3 align="center">NAEO 2023 Demo API</h3>

  <p align="center">
    A simple Spring Boot REST API used as a teaching aid for the <a href="https://naeo.org">NAEO</a> 2023 Advanced IS/SQL Training Workshop.
    <br />
    <br />
    <a href="https://naeo2023api.vanderscheer.us/swagger-ui/index.html">API Docs</a>
    ·
    <a href="https://github.com/kvanderscheer/naeo-2023-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/kvanderscheer/naeo-2023-api/issues">Request Feature</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project

This project, `the naeo-2023-api`, is a Java Spring Boot application that exposes a simple set of REST endpoints for working with the NAEO 2023 Conference agenda. 

**This `the naeo-2023-api` was developed specifically as a teaching aid and should not be considered production ready.**

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

* [![Java 17][Java]][java-url]
* [![aws]][aws-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

There are only a few steps to get the API up running and in a local development environment.

### Prerequisites

* Maven
* Java 17 SDK

### Running the application

1. Clone the repo
   ```sh
   git clone https://github.com/kvanderscheer/naeo-2023-api.git
   ```
2. Run the application locally with maven.
   ```sh
   mvn clean spring-boot:run
   ```
   
Optional: Import the project into your IDE of choice.


<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Sample Data

Sample data is not included in the repository, but can be added by including a `data.sql` in the project's `resources` folder. 

<!-- USAGE EXAMPLES -->
## Usage

This is a simple Spring Boot project that exposes a basic REST API. A demo is deployed to AWS at the following URLs:

* API Endpoint: [https://naeo2023api.vanderscheer.us/v1](https://naeo2023api.vanderscheer.us/v1)
* OpenAPI Endpoint: [https://naeo2023api.vanderscheer.us/api-docs/v1](https://naeo2023api.vanderscheer.us/api-docs/v1)
* Documentation: [https://naeo2023api.vanderscheer.us/swagger-ui/index.html](https://naeo2023api.vanderscheer.us/swagger-ui/index.html)

> For the demo API, method security for POST, PUT, and DELETE operations is provided by an AWS API Gateway using API keys.  

<p align="right">(<a href="#readme-top">back to top</a>)</p>


See the [open issues](https://github.com/kvanderscheer/naeo-2023-api/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Kurt VanderScheer - [kvanderscheer@gmail.com](mailto:kvanderscheer@gmail.com)

Project Link: [https://github.com/kvanderscheer/naeo-2023-api](https://github.com/kvanderscheer/naeo-2023-api)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* []()

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/kvanderscheer/naeo-2023-api.svg?style=for-the-badge

[contributors-url]: https://github.com/kvanderscheer/naeo-2023-api/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/kvanderscheer/naeo-2023-api.svg?style=for-the-badge

[forks-url]: https://github.com/kvanderscheer/naeo-2023-api/network/members

[stars-shield]: https://img.shields.io/github/stars/kvanderscheer/naeo-2023-api.svg?style=for-the-badge

[stars-url]: https://github.com/kvanderscheer/naeo-2023-api/stargazers

[issues-shield]: https://img.shields.io/github/issues/kvanderscheer/naeo-2023-api.svg?style=for-the-badge

[issues-url]: https://github.com/kvanderscheer/naeo-2023-api/issues

[license-shield]: https://img.shields.io/github/license/kvanderscheer/naeo-2023-api.svg?style=for-the-badge

[license-url]: https://github.com/kvanderscheer/naeo-2023-api/blob/master/LICENSE.txt

[java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white

[java-url]: https://www.java.com/en/

[aws]: https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white

[aws-url]: https://aws.amazon.com/