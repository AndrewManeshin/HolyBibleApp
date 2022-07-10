package com.example.holybibleapp.domain.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterId
import com.example.holybibleapp.presentation.chapters.ChapterUi

interface ChapterDomainToUIMapper : Abstract.Mapper.Data<ChapterId, ChapterUi>