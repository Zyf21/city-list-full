import React from 'react';
import {Button} from "@mui/material";


const PostItem = (props) => {
    return (
        <div className="post">
            <div className="post__content">
                <strong className="post__title">{props.post.id}. {props.post.name}</strong>
                <div className="post__btns">
                    <Button
                        disabled={props.dis}
                        variant="contained"onClick={()=>props.open(props.post)}>Update
                    </Button>
                </div>
            </div>
                <div >
                    <img className="post__image" src={props.post.photo}  onError={event => {
                        event.target.src = "https://bitsofco.de/content/images/2018/12/broken-1.png"
                        event.onerror = null
                    }}/>

                </div>

        </div>
    );
};

export default PostItem;
