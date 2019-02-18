import { getAddLendItemAction } from "../../Store/LendStore/LendActions";
import { store } from "../../Store/reduxInit";


export function createLendItem(item){


    let action = getAddLendItemAction(item);
    store.dispatch(action);

}