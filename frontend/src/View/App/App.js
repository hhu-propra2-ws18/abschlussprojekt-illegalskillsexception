import React, { Component } from 'react';
import './App.css';
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";
import Login from './components/LoginComponent/Login';


export class App extends Component {
  render() {
    return (
        <UWPThemeProvider
            theme={getTheme({
              themeName: "light", // set custom theme
              accent: "#0078D7", // set accent color
              useFluentDesign: true, // sure you want use new fluent design.
            })}
        >
          <Login />
        </UWPThemeProvider>
    )
  }
}

export default App;
