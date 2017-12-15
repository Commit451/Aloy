# Aloy

[![Build Status](https://travis-ci.org/Commit451/Aloy.svg?branch=master)](https://travis-ci.org/Commit451/Aloy) [![](https://jitpack.io/v/Commit451/Aloy.svg)](https://jitpack.io/#Commit451/Aloy)

Create a RecyclerView.Adapter without having to subclass.

## Usage
The idea is to be able to have a valid `RecyclerView.Adapter` without having to create a class for the adapter. We do this by providing lambdas to call for `onCreateViewHolder` and `onBindViewHolder`:
```kotlin
//top of file
lateinit var adapter: AloyAdapter<Cheese, CheeseViewHolder>
//later, in onCreate for example:
adapter = AloyAdapter({ parent, viewType ->
    val holder = CheeseViewHolder.inflate(parent)
    holder.itemView.setOnClickListener {
        val cheese = adapter.items[holder.adapterPosition]
        Snackbar.make(root, "${cheese.name} clicked", Snackbar.LENGTH_SHORT)
                .show()
    }
    holder
}, { viewHolder, position, item ->
    viewHolder.bind(item)
})
```
You can also set the `onCreateViewHolder` and `onBindViewHolder` lambdas after construction, just make sure you do so before the first call to `onCreateViewHolder` or `onBindViewHolder`. This is useful for subclassing.
See the sample `app` module for more usage.

## Optional
You can optionally override `getItemViewType` if you need to by setting the `onGetItemViewType` on the adapter.

License
--------

    Copyright 2017 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
