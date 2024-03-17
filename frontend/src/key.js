import React, { useState, useEffect } from 'react';
import { AuthProvider } from './services/AuthContext';
import Main from './pages/main.js';

function Key() {
  return (
    <AuthProvider>
      <Main/>
    </AuthProvider>
  );
}

export default Key;
