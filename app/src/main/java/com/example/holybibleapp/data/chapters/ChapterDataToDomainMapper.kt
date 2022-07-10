package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper.Data<ChapterId, ChapterDomain>