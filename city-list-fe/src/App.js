import './App.css';
import React, {useEffect, useState} from "react";
import axios from "axios";
import {
    TextField,
    Stack,
    TablePagination, Dialog, DialogTitle, DialogContent, Button, DialogActions, DialogContentText
} from "@mui/material";
import PostItem from "./components/PostItem";

const API_BASE_URL = "http://localhost:8080/api/cities";
const API_LOGIN_URL = "http://localhost:8080/api/login";

function App() {

    const [cityList, setCityList] = useState([])
    const [query, setQuery] = useState("")
    const [page, setPage] = useState(0)
    const [pageQty, setPageQty] = useState(0)
    const [rowsPerPage, setRowsPerPage] = React.useState(10);
    const [open, setOpen] = React.useState(false);
    const [loginOpen, setLoginOpen] = React.useState(true);
    const [post, setPost] = React.useState("");
    const [newName, setNewName] = React.useState("");
    const [newUrl, setNewUrl] = React.useState("");
    const [city, setCity] = React.useState(false);
    const [buttonItem, setButtonItem] = React.useState(false);
    const [login, setLogin] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [role, setRole] = React.useState("");


    useEffect(() => {
        if(city){
        axios.get(API_BASE_URL + `?cityName=${query}&pageNumber=${page}&pageSize=${rowsPerPage}`,
            {
                auth: {
                    username: login,
                    password: password
                }
            }
            ).then(
            ({data}) => {
                setCityList(data.content)
                setPageQty(data.totalElements)
            }
        )}

    }, [query, page, rowsPerPage, cityList, city])

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleClickOpen = (postButton) => {
        setPost(postButton.id)
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleUpdate= () => {
        saveData()
        setOpen(false);
    };

    const handleLogin= (e) => {

        axios.get(API_LOGIN_URL,{
            auth: {
                username: login,
                password: password
            }
        }).then(({data}) => {
            setRole(data.authorities[0].authority)
            if(data.authorities[0].authority=="ROLE_ADMIN"){
                 setButtonItem(true)
            }
            setCity(true)
            setLoginOpen(false);
        }).catch(err => {
            alert("User not found")
        });
    };

    const saveData = (e) => {
        const data = {};
        if(post) data.id= post;
        if(newName) data.name= newName;
        if(newUrl) data.photo= newUrl;

       axios.put(API_BASE_URL, data,{
           auth: {
               username: login,
               password: password
           }
       }).then(res => {
                        setNewUrl("")
                        setNewName("")});
    }

    return (
   <div className="container">
       <Dialog open={loginOpen} onClose={handleClose}>
           <DialogTitle>Sign in</DialogTitle>
           <DialogContent>
               <DialogContentText>
                   Enter user name and password
               </DialogContentText>
               <TextField
                   autoFocus
                   margin="dense"
                   id="name"
                   label="User name"
                   type="text"
                   fullWidth
                   variant="standard"
                   onChange={(event)=>setLogin(event.target.value)}
               />
               <TextField
                   autoFocus
                   margin="dense"
                   id="password"
                   label="Password"
                   type="password"
                   fullWidth
                   variant="standard"
                   onChange={(event)=>setPassword(event.target.value)}
               />
           </DialogContent>
           <DialogActions>
               {/*<Button onClick={handleClose}>Cancel</Button >*/}
               <Button disabled={!login || !password} onClick={handleLogin}>Sign in</Button>
           </DialogActions>
       </Dialog>

        <TextField
            fullWidth
            label="Name"
            value={query}
            onChange={(event)=>setQuery(event.target.value)}
            sx={{ marginY: 3 }}
        />
      <Stack spacing={2}>
          {!!pageQty &&(
              <TablePagination
                  rowsPerPageOptions={[5, 10, 25, 100]}
                  component="div"
                  count={pageQty}
                  page={page}
                  rowsPerPage={rowsPerPage}
                  onPageChange={handleChangePage}
                  onRowsPerPageChange={handleChangeRowsPerPage}
                  sx={{ marginY: 3, marginX: "auto" }}
              />)}
          {
              cityList.map(post =><PostItem post={post} url={newUrl} name={newName} dis={buttonItem}  open={handleClickOpen} key={post.id}/>)
          }
          <Dialog open={open} onClose={handleClose}>
              <DialogTitle>Update city</DialogTitle>
              <DialogContent>
                  <DialogContentText>
                      Enter new city data
                  </DialogContentText>
                  <TextField
                      autoFocus
                      margin="dense"
                      id="name"
                      label="City name"
                      type="text"
                      fullWidth
                      variant="standard"
                      onChange={(event)=>setNewName(event.target.value)}
                  />
                  <TextField
                      autoFocus
                      margin="dense"
                      id="name"
                      label="Image url"
                      type="url"
                      fullWidth
                      variant="standard"
                      onChange={(event)=>setNewUrl(event.target.value)}
                  />
              </DialogContent>
              <DialogActions>
                  <Button onClick={handleClose}>Cancel</Button >
                  <Button disabled={!newUrl && !newName} onClick={handleUpdate}>Update</Button>
              </DialogActions>
          </Dialog>
      </Stack>
   </div>
    );
}
export default App;
