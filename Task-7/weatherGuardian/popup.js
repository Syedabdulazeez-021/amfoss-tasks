document.getElementById("get-weather-btn").addEventListener("click", function () {
    const location = document.getElementById("location-input").value;
    if (location) {
      getWeather(location);
    } else {
      alert("Please enter a location.");
    }
  });
  
  function getWeather(location) {
    const apiKey = "a12e44a59f7d19ff9d18274f08ac0906"; // Replace with your actual API key
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${location}&appid=a12e44a59f7d19ff9d18274f08ac0906&units=metric`;
  
    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Location not found");
        }
        return response.json();
      })
      .then((data) => {
        displayWeather(data);
      })
      .catch((error) => {
        alert(error.message);
      });
  }
  
  function displayWeather(data) {
    const weatherResult = document.getElementById("weather-result");
    const { name, main, weather } = data;
    weatherResult.innerHTML = `
      <h3>${name}</h3>
      <p>Temperature: ${main.temp}°C</p>
      <p>Weather: ${weather[0].description}</p>
    `;
  }
  