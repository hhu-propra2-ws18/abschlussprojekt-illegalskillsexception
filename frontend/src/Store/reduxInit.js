export const reducers = combineReducers({
});

export function getDevtoolsExtension(object) {
  if (object.__REDUX_DEVTOOLS_EXTENSION__) {
    return object.__REDUX_DEVTOOLS_EXTENSION__();
  }
  return undefined;
}

//export const store = createStore(reducers, getDevtoolsExtension(window));
export const store = createStore(reducers);